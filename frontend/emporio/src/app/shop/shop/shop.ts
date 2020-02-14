import { Adapter } from '../../adapter';
import { Injectable } from '@angular/core';
import { Product } from '@src/app/product/product/product';

export class Shop {

    shopPIVA: string;
    shopAddress: string;
    shopBusinessName: string;
    shopCategory: ShopCategory;
    shopHeadquarter: string;
    shopLatitude: number;
    shopLongitude: number;

    constructor(shopPIVA: string, shopAddress: string, shopBusinessName: string, shopCategory: ShopCategory,
         shopHeadquarter: string, shopLat: number, shopLng: number) {
        this.shopPIVA = shopPIVA;
        this.shopAddress = shopAddress;
        this.shopBusinessName = shopBusinessName;
        this.shopCategory = shopCategory;
        this.shopHeadquarter = shopHeadquarter;
    }
}

export class Sale {
    product: Product;
    quantity: number;

    constructor(product: Product, quantity: number) {
      this.product = product;
      this.quantity = quantity;
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
export class ShopCategoryAdapter implements Adapter<ShopCategory> {
    adapt(item: any): ShopCategory {
        return new ShopCategory(item.shopCategoryId, item.shopCategoryDescription);
    }
}

@Injectable({
    providedIn: 'root'
})
export class ShopAdapter implements Adapter<Shop> {
    constructor(private catAdapter: ShopCategoryAdapter) {}

    adapt(item: any): Shop {
        return new Shop(item.shopPIVA, item.shopAddress, item.shopBusinessName,
            this.catAdapter.adapt(item.shopCategory), item.shopHeadquarter,
            item.shopLat, item.shopLng);
    }
}
