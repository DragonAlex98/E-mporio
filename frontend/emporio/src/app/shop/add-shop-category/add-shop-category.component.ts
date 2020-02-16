import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ShopService } from '../shop.service';
import { Router } from '@angular/router';
import { NotificationService } from '@src/app/notification.service';

@Component({
  selector: 'app-add-shop-category',
  templateUrl: './add-shop-category.component.html',
  styleUrls: ['./add-shop-category.component.css']
})
export class AddShopCategoryComponent implements OnInit {
  shopCategoryForm = new FormGroup({
    shopCategoryDescription: new FormControl('', Validators.required),
  });

  constructor(private shopService: ShopService, private router: Router,
    private notificationService: NotificationService) { }

  ngOnInit() {
  }

  onSubmit() {
    if (this.shopCategoryForm.invalid) { return; }

    this.shopService.addShopCategory(this.shopCategoryForm.value).subscribe(
      data => {
        this.notificationService.success(data);
        this.router.navigate(['/']);
      }
    );
  }
}
