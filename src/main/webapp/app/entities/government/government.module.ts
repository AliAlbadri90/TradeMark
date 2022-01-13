import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { GovernmentComponent } from './list/government.component';
import { GovernmentDetailComponent } from './detail/government-detail.component';
import { GovernmentUpdateComponent } from './update/government-update.component';
import { GovernmentDeleteDialogComponent } from './delete/government-delete-dialog.component';
import { GovernmentRoutingModule } from './route/government-routing.module';

@NgModule({
  imports: [SharedModule, GovernmentRoutingModule],
  declarations: [GovernmentComponent, GovernmentDetailComponent, GovernmentUpdateComponent, GovernmentDeleteDialogComponent],
  entryComponents: [GovernmentDeleteDialogComponent],
})
export class GovernmentModule {}
