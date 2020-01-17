import { Component, OnInit, Input } from '@angular/core';
import { Delivery } from '../delivery';

@Component({
  selector: 'app-assign-delivery',
  templateUrl: './assign-delivery.component.html',
  styleUrls: ['./assign-delivery.component.css']
})
export class AssignDeliveryComponent implements OnInit {

  @Input()  delivery: Delivery;

  constructor() { }

  ngOnInit() {
  }

  onClick() {

    alert('premuto');

  }

}
