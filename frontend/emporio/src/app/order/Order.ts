import { User } from '@src/app/authentication/models/user';
import { Shop } from '../shop/shop/shop';
import { Product } from '../product/product/product';
import { OrderProductTableItem } from './order-product-table-datasource';
import { Adapter } from '../adapter';
import { Injectable } from '@angular/core';

export class Order {
    id: number;
    parkingAddress: string;
    shop: Shop;
    user: string;

    constructor(id: number, parkingAddress: string, shop: Shop, user: string) {
        this.id = id;
        this.parkingAddress = parkingAddress;
        this.shop = shop;
        this.user = user;
    }
}
@Injectable({
    providedIn: 'root'
})
export class OrderAdapter implements Adapter<Order> {
    adapt(item: any): Order {
        return new Order(item.id, item.parkingAddress, item.shop, item.customerUsername);
    }
}
