import { EventDetailPage } from './../event-detail/event-detail.page';
import { CreateEventPage } from '../create-event/create-event.page';
import { Component, OnInit } from '@angular/core';
import { EventService } from '../../services/EventService';
import { Event } from '../../model/event';
import { ActivatedRoute,Router } from '@angular/router';
import { MenuController, LoadingController, ModalController, NavParams } from '@ionic/angular';

@Component({
  selector: 'app-listEvents',
  templateUrl: 'list-events.page.html',
  styleUrls: ['list-events.page.scss']
})
export class ListEventsPage implements OnInit{

  joined:boolean;
  eventsList: Array<Event>;
  errorMessage: string;
  loader: any;
  isDismiss = false;
  currentModal = null;


  constructor(private eventService: EventService, private router: Router, private menuController: MenuController,
    private loadingCtrl: LoadingController, private modalCtrl: ModalController) {
    }


  ngOnInit(){
    this.menuController.enable(true);
    this.isJoined()
  }

  ionViewWillEnter(){
    this.isJoined();
  }

  isJoined(){
    if (this.router.url != '/home/listJoinedEvents' ){
      this.joined = false;
      this.findNotJoinedEvents(null);
    } 
    else{
      this.joined = true;
      this.findJoinedEvents(null);
    } 
  }

  findJoinedEvents(event){
    this.presentLoading()
    this.eventService.findJoinedEvents().subscribe(data => {
      this.eventsList = data;
      this.eventsList.reverse();
      this.dismiss();
    },err => {
      this.errorMessage = "No s'ha pogut llistar els esdeveniments";
      this.dismiss();
    });

    if (event){
      event.target.complete();
    }
  }

  findNotJoinedEvents(event){
    this.presentLoading()
    this.eventService.findNotJoinedEvents().subscribe(data => {
      this.eventsList = data;
      this.eventsList.reverse();
      this.dismiss();
    },err => {
      this.errorMessage = "No s'ha pogut llistar els esdeveniments";
      this.dismiss();
    });

    if (event){
      event.target.complete();
    }
  }

  async loadEventModal(event){
    const modal = await this.modalCtrl.create({
      component: EventDetailPage,
      componentProps:{
        event: event,
        joined :this.joined
      }
    });
    modal.onDidDismiss().then(() =>{
      this.isJoined();
    });
    return await modal.present();
  }

  
  async createEventModal() {
    const modal = await this.modalCtrl.create({
      component: CreateEventPage
    });
    modal.onDidDismiss().then(() =>{
      this.findNotJoinedEvents(null);
    });
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
