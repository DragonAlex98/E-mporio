import { Component, OnInit } from '@angular/core';
import { Delivery } from '../delivery';
import { DeliveryService } from '../delivery.service';

@Component({
  selector: 'app-delivery-list',
  templateUrl: './delivery-list.component.html',
  styleUrls: ['./delivery-list.component.css']
})
export class DeliveryListComponent implements OnInit {

  deliveryList: Delivery[];

  constructor(private deliveryService: DeliveryService) { }

  ngOnInit() {

    this.getDeliveryList();

  }

  getDeliveryList() {

    this.deliveryService.getDeliveryList().subscribe(
      (data) => {
        this.deliveryList = data;
      },
      (error) => {
        alert(error.error.message);
      }
    );

  }

}
