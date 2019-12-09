import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { Product } from '@src/app/product/product/product';

@Component({
  selector: 'app-order-form',
  templateUrl: './order-form.component.html',
  styleUrls: ['./order-form.component.css']
})
export class OrderFormComponent implements OnInit {
  customerFormGroup = new FormGroup({
    customerName: new FormControl('')
  });

  productList: Product[];

  constructor() { }

  ngOnInit() {
  }

}
