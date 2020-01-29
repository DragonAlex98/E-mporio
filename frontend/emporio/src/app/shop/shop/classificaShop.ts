import { Shop } from "./shop";

export class ClassificaShop {
    
    shop: Shop;
    productsSold: number;

    constructor(shop: Shop, productsSold: number) {
        this.shop = shop;
        this.productsSold = productsSold;
    }
}