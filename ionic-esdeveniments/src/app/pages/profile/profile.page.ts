import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import {User} from '../../model/user';
import {MenuController, LoadingController} from '@ionic/angular';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.page.html',
  styleUrls: ['./profile.page.scss'],
})
export class ProfilePage implements OnInit{

  user: User;
  errorMessage: string;
  loader: any;
  isDataAvailable: boolean = false;
  isDismiss = false;

  constructor( private router: Router, private authService: AuthService, private menuController: MenuController, 
    private loadingCtrl: LoadingController) { }

  ngOnInit(){
    this.menuController.enable(true);
    this.loadUserInfo();
  }

  loadUserInfo(){
    this.presentLoading()
    this.authService.loadUserInfo().subscribe(data => {
      this.user = data;
      this.isDataAvailable = true;
      this.dismiss();
    },err => {
      this.errorMessage = "No s'han pogut carregar les dades";
      this.dismiss();
    });
  }

  logout(){
    this.presentLoading()
    this.authService.logOut().subscribe(data => {
      this.router.navigate(['/login'])
      this.dismiss();
    },err => {
      this.errorMessage = "No s'han pogut tancar la sessió";
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
