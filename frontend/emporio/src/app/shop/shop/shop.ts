import { Adapter } from '../../adapter';
import { Injectable } from '@angular/core';

export class Shop {

    shopPIVA: string;
    shopAddress: string;
    shopBusinessName: string;
    shopCategory: ShopCategory;
    shopHeadquarter: string;

    constructor(shopPIVA: string, shopAddress: string, shopBusinessName: string, shopCategory: ShopCategory, shopHeadquarter: string) {
        this.shopPIVA = shopPIVA;
        this.shopAddress = shopAddress;
        this.shopBusinessName = shopBusinessName;
        this.shopCategory = shopCategory;
        this.shopHeadquarter = shopHeadquarter;
    }
}

export class ShopCategory {
    shopCategoryId: number;
    shopCategoryDescription: string;

    constructor(categoryId: number, description: string) {
      this.shopCategoryId = categoryId;
      this.shopCategoryDescription = description;
    }
  }

@Injectable({
    providedIn: 'root'
})
export class ShopAdapter implements Adapter<Shop> {
    adapt(item: any): Shop {
        return new Shop(item.shopPIVA, item.shopAddress, item.shopBusinessName,
            new ShopCategory(item.shopCategory.shopCategoryId, item.shopCategory.shopCategoryDescription), item.shopHeadquarter);
    }
}
