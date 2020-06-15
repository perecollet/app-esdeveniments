import { Component } from '@angular/core';
import { NgForm } from '@angular/forms'
import { EventService } from '../../services/EventService';
import { Event } from '../../model/event';
import { Router } from '@angular/router';
import { MenuController, LoadingController, ModalController } from '@ionic/angular';

@Component({
  selector: 'app-createEvent',
  templateUrl: 'createEvent.page.html',
  styleUrls: ['createEvent.page.scss']
})

export class CreateEventPage{

  event: Event = new Event();
  errorMessage: string;
  loader: any;
  isDismiss = false;
  activitats=["Futbol","Basquet","Tenis","Padel","Badminton","Rugby","Hoquei","Handbol","Voleibol","Altres"]
  constructor(private eventService: EventService, private router: Router, private menuController: MenuController,
    private loadingCtrl: LoadingController, private modalCtrl: ModalController) {}


  dismissModal() {
    this.modalCtrl.dismiss();
  }

  
  save(f: NgForm){
    this.presentLoading();
    this.eventService.new(this.event).subscribe(data => {
      this.dismiss();
      f.resetForm();
      this.dismissModal();
    },err => {
      this.errorMessage = "No s'ha pogut crear l'event";
      this.loader.dismiss();
    });
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
