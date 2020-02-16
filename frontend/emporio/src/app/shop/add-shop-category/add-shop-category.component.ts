import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ShopService } from '../shop.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-shop-category',
  templateUrl: './add-shop-category.component.html',
  styleUrls: ['./add-shop-category.component.css']
})
export class AddShopCategoryComponent implements OnInit {
  shopCategoryForm = new FormGroup({
    shopCategoryDescription: new FormControl('', Validators.required),
  });

  constructor(private shopService: ShopService, private router: Router) { }

  ngOnInit() {
  }

  onSubmit() {
    if (this.shopCategoryForm.invalid) return;

    this.shopService.addShopCategory(this.shopCategoryForm.value).subscribe(
      data => {
        alert(data);
        this.router.navigate(['/']);
      },
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