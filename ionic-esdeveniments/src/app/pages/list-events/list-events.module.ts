import { EventDetailPageModule } from './../event-detail/event-detail.module';
import { EventDetailPage } from './../event-detail/event-detail.page';
import { CreateEventPageModule } from '../create-event/create-event.module';
import { CreateEventPage } from '../create-event/create-event.page';
import { IonicModule } from '@ionic/angular';
import { RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ListEventsPage } from './list-events.page';
import { ExploreContainerComponentModule } from '../explore-container/explore-container.module';

@NgModule({
  entryComponents: [
    CreateEventPage,
    EventDetailPage,
  ],
  imports: [
    IonicModule,
    CommonModule,
    FormsModule,
    ExploreContainerComponentModule,
    CreateEventPageModule,
    EventDetailPageModule,
    RouterModule.forChild([{ path: '', component: ListEventsPage }])
  ],
  declarations: [ListEventsPage]
})
export class ListEventsPageModule {
}
