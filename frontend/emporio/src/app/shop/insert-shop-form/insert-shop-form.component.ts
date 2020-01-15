import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ShopService } from '../shop.service';
import { ShopCategory } from '../shop/shop';

@Component({
  selector: 'app-insert-shop-form',
  templateUrl: './insert-shop-form.component.html',
  styleUrls: ['./insert-shop-form.component.css']
})
export class InsertShopFormComponent implements OnInit {
  categories: ShopCategory[];

  shopForm: FormGroup = new FormGroup({
    shopPIVA: new FormControl('', Validators.required),
    shopAddress: new FormControl(''),
    shopBusinessName: new FormControl(''),
    shopCategoryDescription: new FormControl(''),
    shopHeadquarter: new FormControl('')
  });

  constructor(private shopService: ShopService) { }

  ngOnInit() {
    this.shopService.getShopCategories().subscribe(
      data => this.categories = data,
      error => alert('Errore di connessione!')
    );
  }

  onSubmit(formValue) {
    this.shopService.addShop(formValue).subscribe(
      data => alert(data),
      error => {
        if ([400].indexOf(error.status) !== -1) {
          alert(error.error.message);
        } else {
          alert('Errore di connessione!');
        }
      }
    );
  }

}
