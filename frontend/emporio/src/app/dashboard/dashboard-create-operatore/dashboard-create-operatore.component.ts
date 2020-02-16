import { Component, OnInit } from '@angular/core';
import { UserService } from '@src/app/authentication/services/user.service';
import { FormGroup, Validators, FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import { NotificationService } from '@src/app/notification.service';

@Component({
  selector: 'app-dashboard-create-operatore',
  templateUrl: './dashboard-create-operatore.component.html',
  styleUrls: ['./dashboard-create-operatore.component.css']
})
export class DashboardCreateOperatoreComponent implements OnInit {

  registerForm = new FormGroup({
    username: new FormControl('', [Validators.required, Validators.minLength(1)]),
    password: new FormControl('', [Validators.required, Validators.minLength(1)]),
  });

  constructor(private userService: UserService, private router: Router, private notificationService: NotificationService) { }

  ngOnInit() {
  }

  onSubmit(formValue: any) {
    if (this.registerForm.invalid) return;

    this.userService.createOperatore(this.registerForm.value).subscribe(
      data => {
        this.notificationService.success('Registrazione effettuata');
        this.router.navigate(['/']);
      });
  }
}
