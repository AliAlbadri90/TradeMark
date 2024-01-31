import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { ComplaintComponent } from './list/complaint.component';
import { ComplaintDetailComponent } from './detail/complaint-detail.component';
import { ComplaintUpdateComponent } from './update/complaint-update.component';
import { ComplaintDeleteDialogComponent } from './delete/complaint-delete-dialog.component';
import { ComplaintRoutingModule } from './route/complaint-routing.module';

@NgModule({
  imports: [SharedModule, ComplaintRoutingModule],
  declarations: [ComplaintComponent, ComplaintDetailComponent, ComplaintUpdateComponent, ComplaintDeleteDialogComponent],
  entryComponents: [ComplaintDeleteDialogComponent],
})
export class ComplaintModule {}
