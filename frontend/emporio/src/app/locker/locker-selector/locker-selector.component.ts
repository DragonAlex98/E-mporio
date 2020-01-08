import { Component, OnInit } from '@angular/core';
import { Locker } from '../Locker';
import { LockerService } from '../locker.service';


@Component({
  selector: 'app-locker-selector',
  templateUrl: './locker-selector.component.html',
  styleUrls: ['./locker-selector.component.css']
})
export class LockerSelectorComponent implements OnInit {

  lockers: Locker[];
  selectedLocker: Locker;

  constructor(private lockerService: LockerService) { }

  ngOnInit() {

    this.getLockers();

  }

  getLockers() {

    this.lockerService.getLockers().subscribe( (lockers) => {this.lockers = lockers; },
                                               (error) => {alert('Impossibile recuperare i lockers'); });

  }

  onLockerChange(event) {

    this.selectedLocker = event.value;

  }

}
