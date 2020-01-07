import { Component, OnInit } from '@angular/core';
import { OrderService } from '../order.service';
import { Order, OrderAdapter } from '../Order';

@Component({
  selector: 'app-order-to-delivery',
  templateUrl: './order-to-delivery.component.html',
  styleUrls: ['./order-to-delivery.component.css']
})
export class OrderToDeliveryComponent implements OnInit {

  orders: Order[];

  constructor(private adapter: OrderAdapter, private orderService: OrderService) { }

  ngOnInit() {

    this.getOrders();

  }

  getOrders() {

    this.orderService.getOrdersToDelivery().subscribe(orders => {this.orders = orders; });

  }


}
