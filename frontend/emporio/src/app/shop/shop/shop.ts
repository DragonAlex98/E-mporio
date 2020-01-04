import { Adapter } from '../../adapter';
import { Injectable } from '@angular/core';

export class Shop {

    shopPIVA: string;
    shopAddress: string;
    shopBusinessName: string;
    shopCategory: number;
    shopHeadquarter: string;

    constructor(shopPIVA: string, shopAddress: string, shopBusinessName: string, shopCategory: number, shopHeadquarter: string) {
        this.shopPIVA = shopPIVA;
        this.shopAddress = shopAddress;
        this.shopBusinessName = shopBusinessName;
        this.shopCategory = shopCategory;
        this.shopHeadquarter = shopHeadquarter;
    }
}

@Injectable({
    providedIn: 'root'
})
export class ShopAdapter implements Adapter<Shop> {
    adapt(item: any): Shop {
        return new Shop(item.pIva, item.add, item.bus, item.cat, item.ss);
    }
}
