import { Component, OnInit } from '@angular/core';
import { OrderService } from '../order.service';

@Component({
  selector: 'app-order-to-delivery',
  templateUrl: './order-to-delivery.component.html',
  styleUrls: ['./order-to-delivery.component.css']
})
export class OrderToDeliveryComponent implements OnInit {

  constructor(private orderService: OrderService) { }

  ngOnInit() {

  }

}
