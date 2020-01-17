import { Component, OnInit, Input } from '@angular/core';
import { Delivery } from '../delivery';

@Component({
  selector: 'app-delivery-details',
  templateUrl: './delivery-details.component.html',
  styleUrls: ['./delivery-details.component.css']
})
export class DeliveryDetailsComponent implements OnInit {

  @Input() delivery: Delivery;

  constructor() { }

  ngOnInit() {
  }

}
