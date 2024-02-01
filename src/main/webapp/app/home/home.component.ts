import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router } from '@angular/router';
import { interval, Subject, Subscription } from 'rxjs';
import { takeUntil } from 'rxjs/operators';

import { AccountService } from 'app/core/auth/account.service';
import { Account } from 'app/core/auth/account.model';
import { TrademarkRegisteredService } from '../entities/trademark-registered/service/trademark-registered.service';

@Component({
  selector: 'jhi-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent implements OnInit, OnDestroy {
  account: Account | null = null;

  subscription?: Subscription;
  public dateNow = new Date();
  public dDay = new Date('Feb 01 2024 12:00:00');
  milliSecondsInASecond = 1000;
  hoursInADay = 24;
  minutesInAnHour = 60;
  SecondsInAMinute = 60;

  public timeDifference: any;
  public secondsToDday: any;
  public minutesToDday: any;
  public hoursToDday: any;
  public daysToDday: any;
  isCountdownFinished = false;
  isVideoEnded = false; // New flag for video ended state
  decreeRegisterCount = '';

  private readonly destroy$ = new Subject<void>();

  constructor(
    private accountService: AccountService,
    private trademarkRegisteredService: TrademarkRegisteredService,
    private router: Router
  ) {}

  // ... existing methods

  ngOnInit(): void {
    this.accountService
      .getAuthenticationState()
      .pipe(takeUntil(this.destroy$))
      .subscribe(account => (this.account = account));
    this.subscription = interval(1000).subscribe(x => {
      this.getTimeDifference();
    });

    this.trademarkRegisteredService.count().subscribe((res: any) => {
      this.decreeRegisterCount = res.body;
    });
  }

  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }

  onVideoEnded(): void {
    this.isVideoEnded = true; // Set the flag to true when video ends
  }
  private getTimeDifference(): void {
    this.timeDifference = this.dDay.getTime() - new Date().getTime();
    if (this.timeDifference > 0) {
      this.allocateTimeUnits(this.timeDifference);
    } else {
      this.isCountdownFinished = true;
      this.subscription?.unsubscribe(); // Stop the interval when countdown is finished
    }
  }

  private allocateTimeUnits(timeDifference: number): void {
    this.secondsToDday = Math.floor((timeDifference / this.milliSecondsInASecond) % this.SecondsInAMinute);
    this.minutesToDday = Math.floor((timeDifference / (this.milliSecondsInASecond * this.minutesInAnHour)) % this.SecondsInAMinute);
    this.hoursToDday = Math.floor(
      (timeDifference / (this.milliSecondsInASecond * this.minutesInAnHour * this.SecondsInAMinute)) % this.hoursInADay
    );
    this.daysToDday = Math.floor(
      timeDifference / (this.milliSecondsInASecond * this.minutesInAnHour * this.SecondsInAMinute * this.hoursInADay)
    );
  }
}
