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
        return this.currentUser != null;
    }

    public canOperateOnShopAsOwner(): boolean {
        return this.isLoggedIn() && this.currentUser.role === Role.Titolare;
    }


    public canOperateOnShopAsEmployee(): boolean {
        return this.isLoggedIn() && this.currentUser.role === Role.Dipendente;
    }

    public canOperateOnShop(): boolean {
        return (this.canOperateOnShopAsOwner() || this.canOperateOnShopAsEmployee());
    }

    public canOperateOnDashboard(): boolean {
        return this.isAdmin() || this.isOperatore();
    }

    public isCustomer(): boolean {
        return this.isLoggedIn() && this.currentUser.role === Role.Acquirente;
    }

    public isFattorino(): boolean {
        return this.isLoggedIn() && this.currentUser.role === Role.Fattorino;
    }

    public isAdmin(): boolean {
        return this.isLoggedIn() && this.currentUser.role === Role.Admin;
    }

    public isOperatore(): boolean {
        return this.isLoggedIn() && this.currentUser.role === Role.OperatoreSistema;
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
