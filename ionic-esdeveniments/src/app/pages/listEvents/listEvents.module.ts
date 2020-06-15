import { CreateEventPageModule } from '../createEvent/createEvent.module';
import { CreateEventPage } from '../createEvent/createEvent.page';
import { IonicModule } from '@ionic/angular';
import { RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ListEventsPage } from './listEvents.page';
import { ExploreContainerComponentModule } from '../explore-container/explore-container.module';

@NgModule({
  entryComponents: [
    CreateEventPage
  ],
  imports: [
    IonicModule,
    CommonModule,
    FormsModule,
    ExploreContainerComponentModule,
    CreateEventPageModule,
    RouterModule.forChild([{ path: '', component: ListEventsPage }])
  ],
  declarations: [ListEventsPage]
})
export class Tab2PageModule {}
