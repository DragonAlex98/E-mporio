import { User } from '@src/app/authentication/models/user';
import { AuthenticationService } from '@src/app/authentication/services/authentication.service';
import { Role } from '@src/app/authentication/models/role';
import { Injectable } from '@angular/core';
import { Shop } from '@src/app/shop/shop/shop';
import { ShopService } from '@src/app/shop/shop.service';

@Injectable({
    providedIn: 'root'
})
export class AuthenticationChecks {
    currentUser: User;
    userShop: Shop;

    constructor(private authenticationService: AuthenticationService, private shopService: ShopService) {
        this.authenticationService.currentUser.subscribe(user => this.currentUser = user);
        this.authenticationService.currentShop.subscribe(shop => this.userShop = shop);
    }

    public isLoggedIn(): boolean {
    if (this.currentUser != null) {
        return true;
    }

    return false;
    }

    public canOperateOnShopAsOwner(): boolean {
    if (this.isLoggedIn() && this.currentUser.role === Role.Titolare) {
        return true;
    }

    return false;
    }


    public canOperateOnShopAsEmployee(): boolean {
    if (this.isLoggedIn() && this.currentUser.role === Role.Dipendente) {
        return true;
    }

    return false;
    }

    public canOperateOnShop(): boolean {
    if (this.canOperateOnShopAsOwner() || this.canOperateOnShopAsEmployee()) {
        return true;
    }

    return false;
    }

    public isCustomer(): boolean {
    if (this.isLoggedIn() && this.currentUser.role === Role.Acquirente) {
        return true;
    }

        return false;
    }

    public isFattorino() {
    if (this.isLoggedIn() && this.currentUser.role === Role.Fattorino) {
        return true;
    }

        return false;
    }

    public getPartitaIva(): string {
        if (this.canOperateOnShop() && this.userShop != null) {
            return this.userShop.shopPIVA;
        }

        return '';
    }

    public getUsername(): string {
        if (this.isLoggedIn()) {
            return this.currentUser.username;
        }

        return '';
    }
}
