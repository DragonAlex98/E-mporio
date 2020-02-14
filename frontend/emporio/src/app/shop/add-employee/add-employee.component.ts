import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ShopService } from '../shop.service';
import { AuthenticationService } from '@src/app/authentication/services/authentication.service';
import { environment } from '@src/environments/environment';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-employee',
  templateUrl: './add-employee.component.html',
  styleUrls: ['./add-employee.component.css']
})
export class AddEmployeeComponent implements OnInit {
  addEmployeeForm: FormGroup = new FormGroup({
    employeeUsername: new FormControl(''),
    ownerUsername: new FormControl(this.authService.currentUserValue.username)
  });

  constructor(private service: ShopService, private authService: AuthenticationService, private router: Router) { }

  ngOnInit() {
  }

  onSubmit(formValue) {
    this.service.addEmployeeToShop(formValue).subscribe(
      data => {
        alert(data);
        this.router.navigate(['/']);
      },
      error => {
        if ([400, 404].indexOf(error.status) !== -1) {
          alert(error.error.message);
        } else {
          alert('Errore di connessione!');
        }
      }
    );
  }

}
