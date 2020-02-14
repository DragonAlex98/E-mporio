import { Component, OnInit, ElementRef, ViewChild } from '@angular/core';
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
  @ViewChild('mapWrapper', {static: false}) mapElement: ElementRef;
  showMap: boolean = false;
  
  piva = '';
  shop: Shop;
  showCatalogo: Boolean;
  showAddProduct: Boolean;
  showAddEmployee: Boolean;
  showAddManager: Boolean;
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
        this.showMap = true;
        this.updateMap();
      }
    );
  }

  updateMap() {
    const lngLat = new google.maps.LatLng(45.4637697, 9.1906177);
    const mapOptions: google.maps.MapOptions = {
      center: lngLat,
      zoom: 16,
      fullscreenControl: false,
      mapTypeControl: false,
      streetViewControl: false
    };
    var map = new google.maps.Map(this.mapElement.nativeElement, mapOptions);
    var marker = new google.maps.Marker({position: lngLat, map: map, title: this.shop.shopBusinessName});

    var infowindow = new google.maps.InfoWindow({
      content: '<div id="content">'+
      '<div id="siteNotice">'+
      '</div>'+
      '<h3 id="thirdHeading" class="thirdHeading">' + this.shop.shopBusinessName + '</h3>'+
      '<div id="bodyContent">'+
      '<p>' + this.shop.shopAddress + '</p>'+
      '</div>'+
      '</div>'
    });
    marker.setMap(map);
    marker.setClickable(true);
    marker.addListener('click', function () {infowindow.open(map, marker)});
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
