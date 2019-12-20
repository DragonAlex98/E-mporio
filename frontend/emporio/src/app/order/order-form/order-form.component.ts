import { Component, OnInit, ViewChild } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { Product } from '@src/app/product/product/product';
import { OrderProductTableDataSource } from '../order-product-table-datasource';
import { User } from '@src/app/authentication/models/user';
import { Role } from '@src/app/authentication/models/role';
import { Order } from '../Order';
import { Shop } from '@src/app/shop/shop/shop';

@Component({
  selector: 'app-order-form',
  templateUrl: './order-form.component.html',
  styleUrls: ['./order-form.component.css']
})
export class OrderFormComponent implements OnInit {
  order: Order;
  customerFormGroup = new FormGroup({
    customerName: new FormControl(''),
    customerCarPosition: new FormControl('')
  });
  usersList: User[] = [];
  shop: Shop;
  dataSource: OrderProductTableDataSource;

  productList: Product[];

  constructor() { }

  ngOnInit() {
    this.dataSource = new OrderProductTableDataSource();
    const u1 = new User(1, 'alfredino91', 'password', Role.Acquirente);
    this.usersList.push(u1);
  }

  addOrder() {
    const user = this.usersList.find(item => item.username === this.customerFormGroup.value.customerName);
    this.shop = new Shop('p111', 'via alfredo alfredini', 'Stonks s.r.l', 81, 'Macerata');
    this.order = new Order(user, this.customerFormGroup.value.customerCarPosition, this.shop, this.dataSource.data);
    console.log(this.order);
  }

}
