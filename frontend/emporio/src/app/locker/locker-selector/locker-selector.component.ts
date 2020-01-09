import { Component, OnInit } from '@angular/core';
import { Locker } from '../Locker';
import { LockerService } from '../locker.service';
import { Posto } from '../Posto';


@Component({
  selector: 'app-locker-selector',
  templateUrl: './locker-selector.component.html',
  styleUrls: ['./locker-selector.component.css']
})
export class LockerSelectorComponent implements OnInit {

  lockers: Locker[];
  selectedLocker: Locker;
  lockerEmptyPlaces: Posto[];

  constructor(private lockerService: LockerService) { }

  ngOnInit() {

    this.getLockers();

  }

  getLockers() {

    this.lockerService.getLockers().subscribe( (lockers) => {this.lockers = lockers; },
                                               (error) => {alert('Impossibile recuperare i lockers'); });

  }

  onLockerChange(event) {

    // TODO Sistemare problema della prima selezione
    this.selectedLocker = event.value;

    if (typeof this.selectedLocker === 'undefined') {
       // Check per evitare che quando si seleziona none venga rifatta la chiamata

    } else {

     this.lockerService.getEmtpyPlaces(this.selectedLocker.lockerId).subscribe(
        (emptyPlaces) => { this.lockerEmptyPlaces =
          emptyPlaces.map((posto) => {
            return new Posto(posto.postoId, posto.nomePosto);
          });
        },
        (error) => {alert('Impossibile recuperare i posti del locker selezionato'); }
      );
    }


  }

}
