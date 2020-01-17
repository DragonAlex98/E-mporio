import { Component, OnInit } from '@angular/core';
import {AuthenticationService} from '@src/app/authentication/services/authentication.service';
import { Router } from '@angular/router';
import { AuthenticationChecks } from '@src/app/AuthenticationChecks';
import { ShopService } from '../shop/shop.service';
import { Shop } from '../shop/shop/shop';

@Component({
  selector: 'app-navbar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {
  events: string[] = [];
  opened: boolean;

  constructor(private authService: AuthenticationService,
    private authChecks: AuthenticationChecks, private router: Router) {
  }

  logout() {
    this.authService.logout();
    this.router.navigate(['/']);
  }

  ngOnInit() {
  }

  public getLoggedUsername(): string {
    return this.authChecks.getUsername();
  }

  public getPartitaIva(): string {
    const string = this.authChecks.getPartitaIva();
    if (string !== '') {
      return string;
    }

    return 'no-shop';
  }

  public isLoggedIn(): boolean {
    return this.authChecks.isLoggedIn();
  }

  public canOperateOnShopAsOwner(): boolean {
    return this.authChecks.canOperateOnShopAsOwner();
  }


  public canOperateOnShopAsEmployee(): boolean {
    return this.authChecks.canOperateOnShopAsEmployee();
  }

  public canOperateOnShop(): boolean {
    return this.authChecks.canOperateOnShop();
  }

  public isCustomer(): boolean {
    return this.authChecks.isCustomer();
  }

  public isFattorino() {
    return this.authChecks.isFattorino();
  }


}
