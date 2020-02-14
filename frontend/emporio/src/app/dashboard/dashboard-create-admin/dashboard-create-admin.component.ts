import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { UserService } from '@src/app/authentication/services/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-dashboard-create-admin',
  templateUrl: './dashboard-create-admin.component.html',
  styleUrls: ['./dashboard-create-admin.component.css']
})
export class DashboardCreateAdminComponent implements OnInit {
  
  registerForm = new FormGroup({
    username: new FormControl('', [Validators.required, Validators.minLength(1)]),
    password: new FormControl('', [Validators.required, Validators.minLength(1)]),
  });

  constructor(private userService: UserService, private router: Router) { }

  ngOnInit() {
  }

  onSubmit(formValue: any) {
    if (this.registerForm.invalid) return;

    this.userService.createAdmin(this.registerForm.value).subscribe(
      data => {
        alert('Registrazione effettuata');
        this.router.navigate(['/']);
      },
      error => {
        alert(error.error.message);
      });
  }
}
