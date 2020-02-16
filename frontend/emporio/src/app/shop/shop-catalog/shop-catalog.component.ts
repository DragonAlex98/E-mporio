import { Component, OnInit, Input } from '@angular/core';
import { ShopService } from '../shop.service';
import { Product } from '@src/app/product/product/product';
import { AuthenticationService } from '@src/app/authentication/services/authentication.service';
import { Role } from '@src/app/authentication/models/role';
import { AuthenticationChecks } from '@src/app/AuthenticationChecks';
import { NotificationService } from '@src/app/notification.service';

@Component({
  selector: 'app-shop-catalog',
  templateUrl: './shop-catalog.component.html',
  styleUrls: ['./shop-catalog.component.css']
})
export class ShopCatalogComponent implements OnInit {
  @Input() piva = '';
  shouldShowDeleteProduct: Boolean;
  productList: Product[];

  constructor(private service: ShopService, private auth: AuthenticationService, private authChecks: AuthenticationChecks,
    private notificationService: NotificationService) { }

  ngOnInit() {
    this.service.getShopCatalog(this.piva).subscribe(
      (products) => {
        this.productList = products;
      }
    );
  }

  shouldShowDeleteShopProductButton(): Boolean {
    return this.authChecks.canOperateOnShop();
  }

  deleteProduct(product: Product) {
    this.service.deleteShopProduct(this.piva, product.productName).subscribe(
      res => this.notificationService.success('Prodotto eliminato correttamente!')
    );
  }

}
