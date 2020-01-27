import { Component, OnInit } from '@angular/core';
import { Shop } from '../shop/shop';
import { ShopService } from '../shop.service';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { switchMap } from 'rxjs/operators';
import { of } from 'rxjs';
import { AuthenticationService } from '@src/app/authentication/services/authentication.service';
import { Role } from '@src/app/authentication/models/role';
import { Product } from '@src/app/product/product/product';
import { AuthenticationChecks } from '@src/app/AuthenticationChecks';

@Component({
  selector: 'app-shop-detail',
  templateUrl: './shop-detail.component.html',
  styleUrls: ['./shop-detail.component.css']
})
export class ShopDetailComponent implements OnInit {
  piva = '';
  shop: Shop;
  showCatalogo: Boolean;
  showAddProduct: Boolean;
  showAddEmployee: Boolean;
  showShopTotalSales: Boolean;

  constructor(private route: ActivatedRoute, private router: Router, private service: ShopService, private auth: AuthenticationService,
    private authChecks: AuthenticationChecks) { }

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

  shouldShowShopSalesButton(): Boolean {
    return this.authChecks.canOperatoreOnShopAsMarketingManager() || this.authChecks.canOperateOnShopAsOwner();
  }

  shouldShowDeleteShopButton(): Boolean {
    return this.authChecks.canOperateOnShopAsOwner();
  }

  deleteShop() {
    this.service.deleteShop().subscribe(
      (res) => {
        console.log(res);
      }
    );
  }

  shouldShowDeleteShopProductButton(): Boolean {
    return this.authChecks.canOperateOnShop();
  }

  showCatalog() {
    this.showCatalogo = true;
    this.showAddProduct = false;
    this.showAddEmployee = false;
    this.showShopTotalSales = false;
  }

  showAddProd() {
    this.showCatalogo = false;
    this.showAddProduct = true;
    this.showAddEmployee = false;
    this.showShopTotalSales = false;
  }

  showAddEmpl() {
    this.showCatalogo = false;
    this.showAddProduct = false;
    this.showAddEmployee = true;
    this.showShopTotalSales = false;
  }

  showShopSales() {
    this.showCatalogo = false;
    this.showAddProduct = false;
    this.showAddEmployee = false;
    this.showShopTotalSales = true;
  }
}
