import { Component, OnInit, Input } from '@angular/core';
import { EventService } from '../../services/EventService';
import { Event } from '../../model/event';
import { Router } from '@angular/router';
import {MenuController, LoadingController, ModalController} from '@ionic/angular';

@Component({
  selector: 'app-event-detail',
  templateUrl: './event-detail.page.html',
  styleUrls: ['./event-detail.page.scss'],
})
export class EventDetailPage implements OnInit {

  @Input() event: Event;
  @Input() joined: boolean;
  errorMessage: string;
  loader: any;
  isDismiss = false;

  constructor(private eventService: EventService, private router: Router, private menuController: MenuController,
    private loadingCtrl: LoadingController, private modalCtrl: ModalController) { }

  ngOnInit(){
    console.log(this.joined);
    this.menuController.enable(true);
  }
  
  dismissModal() {
    this.modalCtrl.dismiss();
  }

  joinEvent(eventId){
    this.presentLoading()
    this.eventService.joinEvent(eventId).subscribe(data => {
      this.dismiss();
      this.dismissModal();
    },err => {
      this.errorMessage = "No s'ha pogut unir-se a l'esdeveniment";
      this.dismiss();
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
