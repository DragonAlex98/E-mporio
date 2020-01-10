import { Component, OnInit, Input } from '@angular/core';
import { Posto } from '../Posto';
import { DeliveryService } from '@src/app/delivery/delivery.service';


@Component({
  selector: 'app-locker-detail',
  templateUrl: './locker-detail.component.html',
  styleUrls: ['./locker-detail.component.css']
})
export class LockerDetailComponent implements OnInit {

  @Input() lockerEmptyPlaces: Posto[];
  selectedPlace: Posto;

  constructor(private deliveryService: DeliveryService) { }

  ngOnInit() {
  }

  onEmptyplaceChange(event) {

    this.selectedPlace = event.value;
    console.log(this.selectedPlace);
    this.deliveryService.setSelectedPlace(this.selectedPlace);

  }

}
