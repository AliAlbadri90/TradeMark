import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { DecreeTypeComponent } from './list/decree-type.component';
import { DecreeTypeDetailComponent } from './detail/decree-type-detail.component';
import { DecreeTypeUpdateComponent } from './update/decree-type-update.component';
import { DecreeTypeDeleteDialogComponent } from './delete/decree-type-delete-dialog.component';
import { DecreeTypeRoutingModule } from './route/decree-type-routing.module';

@NgModule({
  imports: [SharedModule, DecreeTypeRoutingModule],
  declarations: [DecreeTypeComponent, DecreeTypeDetailComponent, DecreeTypeUpdateComponent, DecreeTypeDeleteDialogComponent],
  entryComponents: [DecreeTypeDeleteDialogComponent],
})
export class DecreeTypeModule {}
