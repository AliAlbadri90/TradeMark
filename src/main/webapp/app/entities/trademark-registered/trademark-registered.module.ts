import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { TrademarkRegisteredComponent } from './list/trademark-registered.component';
import { TrademarkRegisteredDetailComponent } from './detail/trademark-registered-detail.component';
import { TrademarkRegisteredUpdateComponent } from './update/trademark-registered-update.component';
import { TrademarkRegisteredDeleteDialogComponent } from './delete/trademark-registered-delete-dialog.component';
import { TrademarkRegisteredRoutingModule } from './route/trademark-registered-routing.module';

@NgModule({
  imports: [SharedModule, TrademarkRegisteredRoutingModule],
  declarations: [
    TrademarkRegisteredComponent,
    TrademarkRegisteredDetailComponent,
    TrademarkRegisteredUpdateComponent,
    TrademarkRegisteredDeleteDialogComponent,
  ],
  entryComponents: [TrademarkRegisteredDeleteDialogComponent],
  exports: [TrademarkRegisteredComponent],
})
export class TrademarkRegisteredModule {}
