import { Component, OnInit } from '@angular/core';
import { Shop } from '../shop/shop';
import { ShopService } from '../shop.service';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { switchMap } from 'rxjs/operators';
import { of } from 'rxjs';
import { AuthenticationService } from '@src/app/authentication/services/authentication.service';
import { Role } from '@src/app/authentication/models/role';
import { Product } from '@src/app/product/product/product';

@Component({
  selector: 'app-shop-detail',
  templateUrl: './shop-detail.component.html',
  styleUrls: ['./shop-detail.component.css']
})
export class ShopDetailComponent implements OnInit {
  piva = '';
  shop: Shop;
  productList: Product[];
  shouldShowDeleteShop: Boolean;
  shouldShowDeleteProduct: Boolean;

  constructor(private route: ActivatedRoute, private router: Router, private service: ShopService, private auth: AuthenticationService) { }

  ngOnInit() {
    this.route.paramMap.pipe(
      switchMap((params: ParamMap) =>
        of(params.get('piva'))
      )
    ).subscribe(piva => {
      this.piva = piva;
    });

    this.service.getShopFromPIVA(this.piva).subscribe(
      (shop) => {
        this.shop = shop;
      }
    );
  }

  shouldShowDeleteShopButton(): Boolean {

    this.auth.currentUser.subscribe(
      (user) => {
        this.shouldShowDeleteShop = (user != null) && (user.role == Role.Titolare);
      }
    );

    return this.shouldShowDeleteShop;
  }

  shouldShowDeleteShopProductButton(): Boolean {
    this.auth.currentUser.subscribe(
      (user) => {
         this.shouldShowDeleteProduct = (user != null) && (user.role == Role.Titolare || user.role == Role.Dipendente);
      }
    );

    return this.shouldShowDeleteProduct;
  }

  deleteShop() {
    this.service.deleteShop().subscribe(
      (res) => {
        console.log(res);
      }
    );
  }

  deleteProduct(product: Product) {
    this.service.deleteShopProduct(this.shop.shopPIVA, product.productName).subscribe(
      (res) => {
        console.log(res);
      }
    );
  }

  showCatalog() {
    this.service.getShopCatalog(this.piva).subscribe(
      (products) => {
        this.productList = products;
      }
    );
  }

}
