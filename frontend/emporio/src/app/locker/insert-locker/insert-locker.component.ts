import { Component, OnInit, AfterViewInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { LockerService } from '../locker.service';
import { Router } from '@angular/router';
import { NotificationService } from '@src/app/notification.service';

@Component({
  selector: 'app-insert-locker',
  templateUrl: './insert-locker.component.html',
  styleUrls: ['./insert-locker.component.css']
})
export class InsertLockerComponent implements OnInit, AfterViewInit {
  lockerForm = new FormGroup({
    lockerStreet: new FormControl('', Validators.required),
    numPosti: new FormControl(1, {validators: [Validators.required, Validators.min(1)]}),
  });

  constructor(private lockerService: LockerService, private router: Router, private notificationService: NotificationService) { }

  ngOnInit() {
  }

  ngAfterViewInit(): void {
    var input = document.getElementById('searchTextField') as HTMLInputElement;
    var autocomplete = new google.maps.places.Autocomplete(input);
    google.maps.event.addListener(autocomplete, 'place_changed', function () {
      var place = autocomplete.getPlace();
      document.getElementById('cityAddr').textContent = place.formatted_address;
      document.getElementById('cityLat').textContent = place.geometry.location.lat().toString();
      document.getElementById('cityLng').textContent = place.geometry.location.lng().toString();
    });
  }

  onSubmit(formValue: any) {
    this.lockerForm.get('lockerStreet').patchValue(document.getElementById('cityAddr').textContent);

    if (this.lockerForm.invalid) return;

    this.lockerService.insertNewLocker(formValue.lockerStreet, formValue.numPosti).subscribe(
      () => {
        this.notificationService.success("Locker Inserito!");
        this.router.navigate(['/']);
      }
    );
  }
}
