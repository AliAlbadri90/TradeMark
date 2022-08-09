import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { DashboardComponent, dashboardRoute } from './';
import { SharedModule } from 'app/shared/shared.module';
import { ChartsModule } from 'ng2-charts';

const ENTITY_STATES = [...dashboardRoute];

@NgModule({
  imports: [SharedModule, RouterModule.forChild(ENTITY_STATES), ChartsModule],
  declarations: [DashboardComponent],
  entryComponents: [DashboardComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  exports: [DashboardComponent],
})
export class DashboardModule {}
