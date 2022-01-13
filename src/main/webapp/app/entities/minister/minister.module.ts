import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { MinisterComponent } from './list/minister.component';
import { MinisterDetailComponent } from './detail/minister-detail.component';
import { MinisterUpdateComponent } from './update/minister-update.component';
import { MinisterDeleteDialogComponent } from './delete/minister-delete-dialog.component';
import { MinisterRoutingModule } from './route/minister-routing.module';

@NgModule({
  imports: [SharedModule, MinisterRoutingModule],
  declarations: [MinisterComponent, MinisterDetailComponent, MinisterUpdateComponent, MinisterDeleteDialogComponent],
  entryComponents: [MinisterDeleteDialogComponent],
})
export class MinisterModule {}
