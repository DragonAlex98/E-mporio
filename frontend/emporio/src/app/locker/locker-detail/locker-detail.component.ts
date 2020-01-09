import { Component, OnInit, Input } from '@angular/core';
import { Posto } from '../Posto';

@Component({
  selector: 'app-locker-detail',
  templateUrl: './locker-detail.component.html',
  styleUrls: ['./locker-detail.component.css']
})
export class LockerDetailComponent implements OnInit {

  @Input() lockerEmptyPlaces: Posto[];
  selectedPlace: Posto;

  constructor() { }

  ngOnInit() {
  }

  onEmptyplaceChange(event) {

    this.selectedPlace = event.value;

  }

}
