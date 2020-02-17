import { Component, OnInit, Input, ViewChild, ElementRef, AfterViewInit } from '@angular/core';
import { Delivery } from '../delivery';
import { LockerService } from '@src/app/locker/locker.service';
import { Locker } from '@src/app/locker/Locker';
import { GoogleMapUtils } from '@src/app/googleMapUtils';
import { NotificationService } from '@src/app/notification.service';

@Component({
  selector: 'app-delivery-details',
  templateUrl: './delivery-details.component.html',
  styleUrls: ['./delivery-details.component.css']
})
export class DeliveryDetailsComponent implements OnInit {
  @Input() delivery: Delivery;
  locker: Locker;

  constructor(private lockerService: LockerService, private notificationService: NotificationService) { }

  ngOnInit() {
    this.getLocker();
  }

  getLocker() {
    if (this.delivery.posto === null) {return; }

    this.lockerService.getLockerByPosto(this.delivery.posto.postoId).subscribe(
      (data) => {this.locker = data; }
    );
  }
}