import { Component, OnInit } from '@angular/core';
import { Locker } from '../Locker';
import { LockerService } from '../locker.service';
import { Posto } from '../Posto';
import { NotificationService } from '@src/app/notification.service';


@Component({
  selector: 'app-locker-selector',
  templateUrl: './locker-selector.component.html',
  styleUrls: ['./locker-selector.component.css']
})
export class LockerSelectorComponent implements OnInit {

  lockers: Locker[];
  selectedLocker: Locker;
  lockerEmptyPlaces: Posto[];

  constructor(private lockerService: LockerService, private notificationService: NotificationService) { }

  ngOnInit() {

    this.getLockers();

  }

  getLockers() {

    this.lockerService.getLockers().subscribe(
      (lockers) => {this.lockers = lockers; }
    );
  }

  onLockerChange(event) {

    this.selectedLocker = event.value;

    if (typeof this.selectedLocker === 'undefined') {
       // Check per evitare che quando si seleziona none venga rifatta la chiamata

    } else {

     this.lockerService.getEmtpyPlaces(this.selectedLocker.lockerId).subscribe(
        (emptyPlaces) => { this.lockerEmptyPlaces =
          emptyPlaces.map((posto) => {
            return new Posto(posto.postoId, posto.nomePosto);
          });
        }
      );
    }


  }

}
