import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { DecreeCategoryComponent } from './list/decree-category.component';
import { DecreeCategoryDetailComponent } from './detail/decree-category-detail.component';
import { DecreeCategoryUpdateComponent } from './update/decree-category-update.component';
import { DecreeCategoryDeleteDialogComponent } from './delete/decree-category-delete-dialog.component';
import { DecreeCategoryRoutingModule } from './route/decree-category-routing.module';

@NgModule({
  imports: [SharedModule, DecreeCategoryRoutingModule],
  declarations: [
    DecreeCategoryComponent,
    DecreeCategoryDetailComponent,
    DecreeCategoryUpdateComponent,
    DecreeCategoryDeleteDialogComponent,
  ],
  entryComponents: [DecreeCategoryDeleteDialogComponent],
})
export class DecreeCategoryModule {}
