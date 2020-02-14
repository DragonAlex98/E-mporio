import { Injectable } from '@angular/core';
import { Product } from '@src/app/product/product/product';

@Injectable({
    providedIn: 'root'
})
export class GoogleMapUtils {
    mapElement: any;
    shopAddress: string;
    shopBusinessName: string;
    shopLatitude: number;
    shopLongitude: number;

    constructor(mapElement: any, shopAddress: string, shopBusinessName: string, shopLatitude: number, shopLongitude: number) {
        this.mapElement = mapElement;
        this.shopAddress = shopAddress;
        this.shopBusinessName = shopBusinessName;
        this.shopLatitude = shopLatitude;
        this.shopLongitude = shopLongitude;
    }

    loadGoogleMap() {
        const lngLat = new google.maps.LatLng(this.shopLatitude, this.shopLongitude);
        const mapOptions: google.maps.MapOptions = {
            center: lngLat,
            zoom: 16,
            fullscreenControl: false,
            mapTypeControl: false,
            streetViewControl: false,
            clickableIcons: false
        };
        var map = new google.maps.Map(this.mapElement, mapOptions);
        var marker = new google.maps.Marker({position: lngLat, map: map, title: this.shopBusinessName});
        
        var infowindow = new google.maps.InfoWindow({
            content: '<div id="content">'+
            '<div id="siteNotice">'+
            '</div>'+
            '<h3 id="thirdHeading" class="thirdHeading">' + this.shopBusinessName + '</h3>'+
            '<div id="bodyContent">'+
            '<p>' + this.shopAddress + '</p>'+
            '</div>'+
            '</div>'
        });

        marker.setMap(map);
        marker.setClickable(true);
        marker.addListener('click', function () {infowindow.open(map, marker)});
    }
}