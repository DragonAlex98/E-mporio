import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { LockerService } from '../locker.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-insert-locker',
  templateUrl: './insert-locker.component.html',
  styleUrls: ['./insert-locker.component.css']
})
export class InsertLockerComponent implements OnInit {

  lockerForm = new FormGroup({
    lockerStreet: new FormControl(''),
    numPosti : new FormControl(''),
  });

  constructor(private lockerService: LockerService, private router: Router) { }

  ngOnInit() {
  }

  onSubmit(formValue: any) {
    if (formValue.lockerStreet.length == 0) {
      alert("Inserire una via!");
      return;
    }
    if (formValue.numPosti <= 0) {
      alert("Il numero di posti deve essere maggiore o uguale a 1");
      return;
    }

    this.lockerService.insertNewLocker(formValue.lockerStreet, formValue.numPosti).subscribe(
      () => {
        alert("Locker Inserito!");
        this.router.navigate(['/']);
      },
      (error) => {
        alert(error.error.message);
      }
    );
  }
}
