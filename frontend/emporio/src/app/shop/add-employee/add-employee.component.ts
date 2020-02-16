import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ShopService } from '../shop.service';
import { AuthenticationService } from '@src/app/authentication/services/authentication.service';
import { environment } from '@src/environments/environment';
import { Router } from '@angular/router';
import { NotificationService } from '@src/app/notification.service';

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

  constructor(private service: ShopService, private authService: AuthenticationService, private router: Router,
    private notificationService: NotificationService) { }

  ngOnInit() {
  }

  onSubmit(formValue) {
    this.service.addEmployeeToShop(formValue).subscribe(
      data => {
        this.notificationService.success(data);
        this.router.navigate(['/']);
      }
    );
  }

}
