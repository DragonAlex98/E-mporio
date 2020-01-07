import { Component, OnInit, Input } from '@angular/core';
import {Order} from '@src/app/order/Order';

@Component({
  selector: 'app-order-to-delivery-details',
  templateUrl: './order-to-delivery-details.component.html',
  styleUrls: ['./order-to-delivery-details.component.css']
})
export class OrderToDeliveryDetailsComponent implements OnInit {

  @Input() order: Order; // riceve un ordine della lista e lo visualizza senza fare altro

  constructor() { }

  ngOnInit() {
  }

}
