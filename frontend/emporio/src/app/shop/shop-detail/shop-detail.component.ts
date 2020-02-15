import { Component, OnInit, ElementRef, ViewChild } from '@angular/core';
import { Shop } from '../shop/shop';
import { ShopService } from '../shop.service';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { switchMap } from 'rxjs/operators';
import { of, Observable, BehaviorSubject } from 'rxjs';
import { AuthenticationService } from '@src/app/authentication/services/authentication.service';
import { Role } from '@src/app/authentication/models/role';
import { Product } from '@src/app/product/product/product';
import { AuthenticationChecks } from '@src/app/AuthenticationChecks';
import { GoogleMapUtils } from '@src/app/googleMapUtils';

@Component({
  selector: 'app-shop-detail',
  templateUrl: './shop-detail.component.html',
  styleUrls: ['./shop-detail.component.css']
})
export class ShopDetailComponent implements OnInit {
  @ViewChild('mapWrapper', {static: false}) mapElement: ElementRef;
  private showMapSubject: BehaviorSubject<Boolean>;
  public showMap: Observable<Boolean>;
  
  piva = '';
  shop: Shop;
  showCatalogo: Boolean;
  showAddProduct: Boolean;
  showAddEmployee: Boolean;
  showAddManager: Boolean;
  showShopTotalSales: Boolean;

  constructor(private route: ActivatedRoute, private router: Router, private service: ShopService, private auth: AuthenticationService,
    private authChecks: AuthenticationChecks) {
      this.showMapSubject = new BehaviorSubject<Boolean>(false);
      this.showMap = this.showMapSubject.asObservable();
    }

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
        this.showMapSubject.next(true);
        this.updateMap();
      }
    );
  }

  updateMap() {
    if (!this.showMapSubject.value) return;

    var mapUtils = new GoogleMapUtils(this.mapElement.nativeElement);

    mapUtils.loadGoogleMap();

    var marker = mapUtils.addMarker(this.shop.shopLatitude, this.shop.shopLongitude, this.shop);

    mapUtils.addInfoWindow(marker, this.shop);

    mapUtils.center(marker);

    document.getElementById('map').style.display = "block";
  }

  shouldShowButtons(): Boolean {
    return this.authChecks.userShop && this.authChecks.userShop.shopPIVA == this.shop.shopPIVA;
  }

  shouldShowShopSalesButton(): Boolean {
    return this.authChecks.canOperatoreOnShopAsMarketingManager() || this.authChecks.canOperateOnShopAsOwner();
  }

  shouldShowDeleteShopButton(): Boolean {
    return this.authChecks.canOperateOnShopAsOwner();
  }

  shouldShowAddManagerButton(): Boolean {
    return this.authChecks.canOperateOnShopAsOwner();
  }

  deleteShop() {
    this.service.deleteShop().subscribe(
      () => {
        this.auth.removeShop();
        this.router.navigate(['/']);
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
    this.showAddManager = false;
    this.showShopTotalSales = false;
  }

  showAddProd() {
    this.showCatalogo = false;
    this.showAddProduct = true;
    this.showAddEmployee = false;
    this.showAddManager = false;
    this.showShopTotalSales = false;
  }

  showAddEmpl() {
    this.showCatalogo = false;
    this.showAddProduct = false;
    this.showAddEmployee = true;
    this.showAddManager = false;
    this.showShopTotalSales = false;
  }

  showAddMarketingManager() {
    this.showCatalogo = false;
    this.showAddProduct = false;
    this.showAddEmployee = false;
    this.showAddManager = true;
    this.showShopTotalSales = false;
  }

  showShopSales() {
    this.showCatalogo = false;
    this.showAddProduct = false;
    this.showAddEmployee = false;
    this.showAddManager = false;
    this.showShopTotalSales = true;
  }
}
