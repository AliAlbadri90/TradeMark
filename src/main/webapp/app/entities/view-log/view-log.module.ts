import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { ViewLogComponent } from './list/view-log.component';
import { ViewLogDetailComponent } from './detail/view-log-detail.component';
import { ViewLogUpdateComponent } from './update/view-log-update.component';
import { ViewLogDeleteDialogComponent } from './delete/view-log-delete-dialog.component';
import { ViewLogRoutingModule } from './route/view-log-routing.module';

@NgModule({
  imports: [SharedModule, ViewLogRoutingModule],
  declarations: [ViewLogComponent, ViewLogDetailComponent, ViewLogUpdateComponent, ViewLogDeleteDialogComponent],
  entryComponents: [ViewLogDeleteDialogComponent],
})
export class ViewLogModule {}
