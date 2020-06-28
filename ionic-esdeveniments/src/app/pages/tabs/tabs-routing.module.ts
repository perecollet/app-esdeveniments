import { NavParams } from '@ionic/angular';
import { ListEventsPage } from './../list-events/list-events.page';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TabsPage } from './tabs.page';

const routes: Routes = [
  {
    path: '',
    component: TabsPage,
    children: [
      /*{
        path: 'listJoinedEvents',
        children: [
          {
            path: '',
            loadChildren: '../list-events/list-events.module#ListEventsPageModule'
          }
        ]
      },*/
      {
        path: '',
        children: [
          {
            path: '',
            loadChildren: '../list-events/list-events.module#ListEventsPageModule'
          }
        ]
      },
      {
        path: 'listJoinedEvents',
        children: [
          {
            path:'',
            loadChildren: '../list-events/list-events.module#ListEventsPageModule'
          }
        ]
      },
      {
        path: 'listEvents',
        children: [
          {
            path:'',
            loadChildren: '../list-events/list-events.module#ListEventsPageModule'
          }
        ]
      },
      {
        path: 'profile',
        children: [
          {
            path:'',
            loadChildren: '../profile/profile.module#ProfilePageModule'
          }
        ]
      },
    ]
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TabsPageRoutingModule {}
