import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-insert-shop-form',
  templateUrl: './insert-shop-form.component.html',
  styleUrls: ['./insert-shop-form.component.css']
})
export class InsertShopFormComponent implements OnInit {
  shopForm: FormGroup = new FormGroup({
    shopPIVA: new FormControl('', Validators.required),
    shopRagioneSociale: new FormControl(''),
    shopCategory: new FormControl(''),
    shopSedeOperativa: new FormControl('')
  });

  constructor() { }

  ngOnInit() {
  }

  onSubmit(formValue) {
    return null;
  }

}
