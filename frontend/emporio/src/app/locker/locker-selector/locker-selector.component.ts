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
  lockerEmptyPlaces: number;

  constructor(private lockerService: LockerService) { }

  ngOnInit() {

    this.getLockers();

  }

  getLockers() {

    this.lockerService.getLockers().subscribe( (lockers) => {this.lockers = lockers; },
                                               (error) => {alert('Impossibile recuperare i lockers'); });

  }

  onLockerChange(event) {
    // TODO METTERE CONTROLLO VALORE LOCKER
    this.selectedLocker = event.value;
    this.lockerService.getEmtpyPlaces(this.selectedLocker.lockerId).subscribe(
      (emptyPlaces) => {this.lockerEmptyPlaces = emptyPlaces; },
      (error) => {alert('Impossibile recuperare i posti del locker selezionato'); }
    );

  }

}
