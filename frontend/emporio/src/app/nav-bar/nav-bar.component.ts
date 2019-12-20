import { Component, OnInit } from '@angular/core';
import {AuthenticationService} from '@src/app/authentication/services/authentication.service'
import { User } from '../authentication/models/user';

@Component({
  selector: 'app-navbar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {
  events: string[] = [];
  opened: boolean;
  currentUser: User;

  shouldRun = [/(^|\.)plnkr\.co$/, /(^|\.)stackblitz\.io$/].some(h => h.test(window.location.host));

  constructor(private authenticationService: AuthenticationService) {

    this.authenticationService.currentUser.subscribe(user => this.currentUser = user);
    console.log(this.currentUser);
   }

   logout() {
    this.authenticationService.logout();
   }

  ngOnInit() {
  }


}
