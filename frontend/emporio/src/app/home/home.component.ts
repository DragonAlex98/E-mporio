import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { ClassificaShop } from '../shop/shop/classificaShop';
import { ShopService } from '../shop/shop.service';
import { Shop } from '../shop/shop/shop';
import { GoogleMapUtils } from '../googleMapUtils';
import { NotificationService } from '../notification.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit {
  @ViewChild('mapElement', {static: false}) mapElement: ElementRef;
  title = 'E-mporio';

  shopList: Shop[];
  classificaShopList: ClassificaShop[];

  constructor(private shopService: ShopService, private notificationService: NotificationService) { }

  ngOnInit() {
    this.shopService.getClassifica().subscribe(
      data => this.classificaShopList = data
    );

    this.shopService.getAllExistingShops().subscribe(
      (data) => {
        this.shopList = data;
        this.updateMap();
      }
    );
  }

  updateMap() {
    const mapUtils = new GoogleMapUtils(this.mapElement.nativeElement);

    mapUtils.loadGoogleMap();

    this.shopList.forEach(element => {
        const marker = mapUtils.addMarker(element.shopLatitude, element.shopLongitude, element);
        mapUtils.addInfoWindow(marker, element);
      });

    navigator.geolocation.getCurrentPosition(position => {
      mapUtils.center(new google.maps.Marker({position: new google.maps.LatLng(position.coords.latitude, position.coords.longitude)}));
      mapUtils.setZoom(6);
    }, (error) => {
      console.log(error);
      mapUtils.center(new google.maps.Marker({position: new google.maps.LatLng(43, 13)}))
      mapUtils.setZoom(4);
    });
  }
}