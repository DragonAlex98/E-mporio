import { Shop, ShopAdapter } from '../shop/shop/shop';
import { Product, ProductAdapter } from '../product/product/product';
import { Adapter } from '../adapter';
import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';

export class OrderHistory {
    id: number;
    carPosition: string;
    shop: Shop;
    customerUsername: string;
    orderLine: OrderLine[];
    deliveryState: string;
    lockerId: number;
    lockerAddress: string;
    lockerSpaceName: string;

    constructor(id: number, carPosition: string, shop: Shop, customerUsername: string, orderLine: OrderLine[],
                deliveryState: string, lockerId: number, lockerAddress: string, lockerSpaceName: string) {
        this.id = id;
        this.carPosition = carPosition;
        this.shop = shop;
        this.customerUsername = customerUsername;
        this.orderLine = orderLine;
        this.deliveryState = deliveryState;
        this.lockerId = lockerId;
        this.lockerAddress = lockerAddress;
        this.lockerSpaceName = lockerSpaceName;
    }
}

export class OrderLine {
    product: Product;
    quantity: number;

    constructor(product: Product, quantity: number) {
        this.product = product;
        this.quantity = quantity;
    }
}

@Injectable({
    providedIn: 'root'
})
export class OrderLineAdapter implements Adapter<OrderLine> {
    constructor(private productAdapter: ProductAdapter) { }

    adapt(item: any): OrderLine {
        return new OrderLine(this.productAdapter.adapt(item.product), item.quantity);
    }
}

@Injectable({
    providedIn: 'root'
})
export class OrderHistoryAdapter implements Adapter<OrderHistory> {
    constructor(private shopAdapter: ShopAdapter, private productAdapter: ProductAdapter,
        private lineAdapter: OrderLineAdapter) { }

    adapt(item: any): OrderHistory {
        return new OrderHistory(item.orderId, item.parkingAddress, this.shopAdapter.adapt(item.orderShop),
                                item.orderCustomerUsername, item.orderProductsLineList.map(
                                    (elem: any) => this.lineAdapter.adapt(elem)
                                ),
                                item.statoConsegna, item.lockerId, item.lockerAddress, item.nomePosto);
    }
}
