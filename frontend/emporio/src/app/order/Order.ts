import { User } from '@src/app/authentication/models/user';
import { Shop } from '../shop/shop/shop';
import { Product } from '../product/product/product';
import { OrderProductTableItem } from './order-product-table-datasource';
import { Adapter } from '../adapter';
import { Injectable } from '@angular/core';

export class Order {
    user: User;
    userCarPosition: string;
    shop: Shop;
    orderProductList: OrderProductTableItem[];

    constructor(user: User, userCarPosition: string, shop: Shop, orderProductList: OrderProductTableItem[]) {
        this.user = user;
        this.userCarPosition = userCarPosition;
        this.shop = shop;
        this.orderProductList = orderProductList;
    }
}
@Injectable({
    providedIn: 'root'
})
export class OrderAdapter implements Adapter<Order> {
    adapt(item: any): Order {
        return new Order(null, null, null, null);
    }
}
