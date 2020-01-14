import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ProductService } from '../product.service';
import { ProductCategory } from '../product/product';
@Component({
  selector: 'app-insert-product-form',
  templateUrl: './insert-product-form.component.html',
  styleUrls: ['./insert-product-form.component.css'],
})
export class InsertProductFormComponent implements OnInit {
  categories: ProductCategory[];


  productForm = new FormGroup({
    productName: new FormControl('', Validators.required),
    productCategoryName : new FormControl(''),
  });

  constructor(private productService: ProductService) { }

  ngOnInit() {
    this.productService.getCategories().subscribe(
      data => this.categories = data,
      error => alert('Errore di connessione!')
    );
  }

  onSubmit(productValue) {
    this.productService.addProduct(productValue).subscribe(
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
