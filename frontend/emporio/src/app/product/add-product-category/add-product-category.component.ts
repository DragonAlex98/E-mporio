import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ProductService } from '../product.service';
import { Router } from '@angular/router';
import { NotificationService } from '@src/app/notification.service';

@Component({
  selector: 'app-add-product-category',
  templateUrl: './add-product-category.component.html',
  styleUrls: ['./add-product-category.component.css']
})
export class AddProductCategoryComponent implements OnInit {
  productCategoryForm = new FormGroup({
    description: new FormControl('', Validators.required),
  });

  constructor(private productService: ProductService, private router: Router,
    private notificationService: NotificationService) { }

  ngOnInit() {
  }

  onSubmit() {
    if (this.productCategoryForm.invalid) { return; }

    this.productService.addProductCategory(this.productCategoryForm.value).subscribe(
      data => {
        this.notificationService.success(data);
        this.router.navigate(['/']);
      }
    );
  }
}
