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
    productCategory : new FormControl(''),
    productPrice : new FormControl(''),
    productQuantity : new FormControl('')
  });

  constructor(private productService: ProductService) { }

  ngOnInit() {
    this.productService.getCategories()
    .subscribe((ProductCategoryListData) => this.categories = ProductCategoryListData.map(
      item => {
        return new ProductCategory(
          item.categoryId,
          item.description
        );
      }
    ));
  }

  onSubmit(productValue) {
    console.log(productValue);
    this.productService.addProduct(productValue).subscribe(item => console.log(item));
  }

}
