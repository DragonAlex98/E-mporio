import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ShopService } from '../shop.service';
import { AuthenticationService } from '@src/app/authentication/services/authentication.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-marketing-manager',
  templateUrl: './add-marketing-manager.component.html',
  styleUrls: ['./add-marketing-manager.component.css']
})
export class AddMarketingManagerComponent implements OnInit {
  addManagerForm: FormGroup = new FormGroup({
    managerUsername: new FormControl(''),
    ownerUsername: new FormControl(this.authService.currentUserValue.username)
  });

  constructor(private service: ShopService, private authService: AuthenticationService, private router: Router) { }

  ngOnInit() {
  }

  onSubmit(formValue) {
    this.service.addMarketingManagerToShop(formValue).subscribe(
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
