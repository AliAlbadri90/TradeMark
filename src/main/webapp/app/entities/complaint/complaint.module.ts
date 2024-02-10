import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { ComplaintComponent } from './list/complaint.component';
import { ComplaintDetailComponent } from './detail/complaint-detail.component';
import { ComplaintUpdateComponent } from './update/complaint-update.component';
import { ComplaintDeleteDialogComponent } from './delete/complaint-delete-dialog.component';
import { ComplaintRoutingModule } from './route/complaint-routing.module';
import { ComplaintNewPublicComponent } from './new-public/complaint-new-public.component';
import { ComplaintPrintComponent } from './print/complaint-print.component';
import { QRCodeModule } from 'angularx-qrcode';

@NgModule({
  imports: [SharedModule, ComplaintRoutingModule, QRCodeModule],
  declarations: [
    ComplaintComponent,
    ComplaintDetailComponent,
    ComplaintUpdateComponent,
    ComplaintDeleteDialogComponent,
    ComplaintNewPublicComponent,
    ComplaintPrintComponent,
  ],
  entryComponents: [ComplaintDeleteDialogComponent],
})
export class ComplaintModule {}
