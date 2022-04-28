import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { dashboardRoute } from './';
import { DashboardComponent } from './dashboard.component';
import { SharedModule } from 'app/shared/shared.module';
import { ChartsModule } from 'ng2-charts';

const ENTITY_STATES = [...dashboardRoute];

@NgModule({
  imports: [SharedModule, RouterModule.forChild(ENTITY_STATES), ChartsModule],
  declarations: [DashboardComponent],
  entryComponents: [DashboardComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
})
export class DashboardModule {}
