import { User } from '@src/app/authentication/models/user';
import { Shop } from '../shop/shop/shop';
import { Product } from '../product/product/product';
import { OrderProductTableItem } from './order-product-table-datasource';

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
