import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { TrademarkDecreeComponent } from './list/trademark-decree.component';
import { TrademarkDecreeDetailComponent } from './detail/trademark-decree-detail.component';
import { TrademarkDecreeUpdateComponent } from './update/trademark-decree-update.component';
import { TrademarkDecreeDeleteDialogComponent } from './delete/trademark-decree-delete-dialog.component';
import { TrademarkDecreeRoutingModule } from './route/trademark-decree-routing.module';
import {TrademarkDecreePublicComponent} from "./list_public/trademark-decree-public.component";

@NgModule({
  imports: [SharedModule, TrademarkDecreeRoutingModule],
  declarations: [
    TrademarkDecreeComponent,
    TrademarkDecreePublicComponent,
    TrademarkDecreeDetailComponent,
    TrademarkDecreeUpdateComponent,
    TrademarkDecreeDeleteDialogComponent,
  ],
  entryComponents: [TrademarkDecreeDeleteDialogComponent],
})
export class TrademarkDecreeModule {}
