import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ProductService } from '../product.service';
import { ProductCategory } from '../product/product';
import { Router } from '@angular/router';
import { NotificationService } from '@src/app/notification.service';
@Component({
  selector: 'app-insert-product-form',
  templateUrl: './insert-product-form.component.html',
  styleUrls: ['./insert-product-form.component.css'],
})
export class InsertProductFormComponent implements OnInit {
  categories: ProductCategory[];


  productForm = new FormGroup({
    productName: new FormControl('', Validators.required),
    productCategoryName : new FormControl('', Validators.required),
  });

  constructor(private productService: ProductService, private router: Router,
    private notificationService: NotificationService) { }

  ngOnInit() {
    this.productService.getCategories().subscribe(
      data => this.categories = data,
    );
  }

  onSubmit(productValue) {
    this.productService.addProduct(productValue).subscribe(
      data => {
        this.notificationService.success(data);
        this.router.navigate(['/']);
      }
    );
  }

}
