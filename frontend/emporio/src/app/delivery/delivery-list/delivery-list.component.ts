import { Component, OnInit } from '@angular/core';
import { Delivery } from '../delivery';

@Component({
  selector: 'app-delivery-list',
  templateUrl: './delivery-list.component.html',
  styleUrls: ['./delivery-list.component.css']
})
export class DeliveryListComponent implements OnInit {

  deliveryList: Delivery[];

  constructor() { }

  ngOnInit() {
  }

  

}
