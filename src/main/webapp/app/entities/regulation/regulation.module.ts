import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { RegulationComponent } from './list/regulation.component';
import { RegulationDetailComponent } from './detail/regulation-detail.component';
import { RegulationUpdateComponent } from './update/regulation-update.component';
import { RegulationDeleteDialogComponent } from './delete/regulation-delete-dialog.component';
import { RegulationRoutingModule } from './route/regulation-routing.module';

@NgModule({
  imports: [SharedModule, RegulationRoutingModule],
  declarations: [RegulationComponent, RegulationDetailComponent, RegulationUpdateComponent, RegulationDeleteDialogComponent],
  entryComponents: [RegulationDeleteDialogComponent],
})
export class RegulationModule {}
