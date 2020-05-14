import { Component, OnInit } from '@angular/core';
import { EventService } from './../services/EventService';
import { Event } from '../model/event';
import { Router } from '@angular/router';
import { MenuController, LoadingController} from '@ionic/angular';

@Component({
  selector: 'app-createEvent',
  templateUrl: 'createEvent.page.html',
  styleUrls: ['createEvent.page.scss']
})
export class CreateEventPage implements OnInit{

  event: Event = new Event();
  errorMessage: string;
  loader: any;

  constructor(private eventService: EventService, private router: Router, private menuController: MenuController,
    private loadingCtrl: LoadingController) {}

  ngOnInit() {
    this.menuController.enable(false);
  }

  dismiss (){
    this.loader.dismiss();
  }

  save(){
    this.presentLoading();
    this.eventService.new(this.event).subscribe(data => {
      this.loader.dismiss();
      //this.router.navigate(['/listEvents']);

    },err => {
      this.errorMessage = "No s'ha pogut crear l'event";
      this.loader.dismiss();
    });
  }

  async presentLoading() {
    this.loader = await this.loadingCtrl.create({
      message: 'Please wait...'
    });
    await this.loader.present();
  }

}
