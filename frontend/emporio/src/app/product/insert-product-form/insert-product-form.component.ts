import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-insert-product-form',
  templateUrl: './insert-product-form.component.html',
  styleUrls: ['./insert-product-form.component.css'],
})
export class InsertProductFormComponent implements OnInit {

  productForm = new FormGroup({
    Descrizione : new FormControl('', Validators.required),
    Categoria : new FormControl(''),
    Prezzo : new FormControl(''),
    Quantita : new FormControl('')
  });

  constructor() { }

  ngOnInit() {
  }

}
