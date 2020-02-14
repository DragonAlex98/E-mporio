import { Component, OnInit, Input, ViewChild, ElementRef, AfterViewInit } from '@angular/core';
import { Delivery } from '../delivery';
import { LockerService } from '@src/app/locker/locker.service';
import { Locker } from '@src/app/locker/Locker';
import { GoogleMapUtils } from '@src/app/googleMapUtils';

@Component({
  selector: 'app-delivery-details',
  templateUrl: './delivery-details.component.html',
  styleUrls: ['./delivery-details.component.css']
})
export class DeliveryDetailsComponent implements OnInit, AfterViewInit {
  @ViewChild('mapWrapper', {static: false}) mapElement: ElementRef;

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

  ngAfterViewInit(): void {
    var mapUtils = new GoogleMapUtils(this.mapElement.nativeElement, this.delivery.ordine.shop.shopAddress,
      this.delivery.ordine.shop.shopBusinessName, this.delivery.ordine.shop.shopLatitude,
      this.delivery.ordine.shop.shopLongitude);

    mapUtils.loadGoogleMap();

    document.getElementById('map').style.display = "block";
  }
}