import { CreateEventPage } from '../createEvent/createEvent.page';
import { Component, OnInit } from '@angular/core';
import { EventService } from '../../services/EventService';
import { Event } from '../../model/event';
import { Router } from '@angular/router';
import { MenuController, LoadingController, ModalController } from '@ionic/angular';

@Component({
  selector: 'app-listEvents',
  templateUrl: 'listEvents.page.html',
  styleUrls: ['listEvents.page.scss']
})
export class ListEventsPage {

  event: Event = new Event();
  errorMessage: string;
  loader: any;
  isDismiss = false;
  currentModal = null;

  constructor(private eventService: EventService, private router: Router, private menuController: MenuController,
    private loadingCtrl: LoadingController, private modalCtrl: ModalController) {}


  async createModal() {
    const modal = await this.modalCtrl.create({
      component: CreateEventPage
    });
    this.currentModal = modal;
    return await modal.present();
  }

  async presentLoading() {
    this.loader = await this.loadingCtrl.create({
      message: 'Please wait...'
    });
    await this.loader.present().then(()=> {
      if(this.isDismiss){
        this.loader.dismiss();
      }
    });
  }

  async dismiss() {
    this.isDismiss = true;
    if(!this.loader){
      return;
    }
  return await this.loader.dismiss().then(() => console.log('dismissed'));
  }


}
