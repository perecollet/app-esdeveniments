import { NgForm } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import {User} from '../../model/user';
import { Router } from '@angular/router';
import {MenuController, LoadingController} from '@ionic/angular';

@Component({
  selector: 'app-register',
  templateUrl: './register.page.html',
  styleUrls: ['./register.page.scss'],
})
export class RegisterPage implements OnInit {
  user: User = new User();
  errorMessage: string;
  loader: any;
  isDismiss = false;

  constructor(private authService: AuthService, private router: Router,
  private menuController: MenuController, private loadingCtrl: LoadingController) { }

  ngOnInit() {
    this.menuController.enable(false);
  }

  register(f: NgForm){
    console.log(this.user.birthday)
    this.presentLoading();
    this.authService.register(this.user).subscribe(data=> {
      this.dismiss();
      f.resetForm();
      this.router.navigate(['/login']);
    },err => {
      this.errorMessage = "No s'ha pogut registrar l'usuari";
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