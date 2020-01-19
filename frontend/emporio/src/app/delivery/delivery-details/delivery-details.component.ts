import { Component, OnInit, Input } from '@angular/core';
import { Delivery } from '../delivery';
import { LockerService } from '@src/app/locker/locker.service';
import { Locker } from '@src/app/locker/Locker';

@Component({
  selector: 'app-delivery-details',
  templateUrl: './delivery-details.component.html',
  styleUrls: ['./delivery-details.component.css']
})
export class DeliveryDetailsComponent implements OnInit {

  @Input() delivery: Delivery;
  locker: Locker;

  constructor(private lockerService: LockerService) { }

  ngOnInit() {

    this.getLocker();

  }

  getLocker() {

    if (this.delivery.posto === null) {return; }

    this.lockerService.getLockerByPosto(this.delivery.posto.postoId).subscribe(
      (data) => {this.locker = data; },
      (error) => {alert(error.error.message); }
    );

  }



}
