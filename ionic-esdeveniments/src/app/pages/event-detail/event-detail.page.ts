import { Component, OnInit, Input } from '@angular/core';
import { alertController } from '@ionic/core';
import { EventService } from '../../services/EventService';
import { Event } from '../../model/event';
import { User } from '../../model/user';
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
  show = false;
  creator:boolean;
  errorMessage: string;
  loader: any;
  isDismiss = false;

  constructor(private eventService: EventService, private router: Router, private menuController: MenuController,
    private loadingCtrl: LoadingController, private modalCtrl: ModalController) { }

  ngOnInit(){
    this.menuController.enable(true);
    if (this.event.creatorId["value"] == localStorage.getItem("userId") ) this.creator = true;
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

  leaveEvent(eventId){
    this.presentLoading()
    this.eventService.leaveEvent(eventId).subscribe(data => {
      this.dismiss();
      this.dismissModal();
    },err => {
      this.errorMessage = "No s'ha pogut abandonar l'esdeveniment";
      this.dismiss();
    });
  }

  deleteEvent(eventId){
    this.presentLoading()
    this.eventService.deleteEvent(eventId).subscribe(data => {
      this.dismiss();
      this.dismissModal();
    },err => {
      this.errorMessage = "No s'ha pogut eliminar l'esdeveniment";
      this.dismiss();
    });
  }

  showParticipants(){
    this.show = !this.show;
  }

  async showUserDetails(user: User) {
    const alert = await alertController.create({
      header: user.name +' '+ user.surname,
      message: user.description + '<br><br>Email: ' + user.email + '<br>Telèfon: ' + user.phone,
      buttons: ['okei']
    });

    await alert.present();
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
