import { Shop } from "./shop/shop/shop";

export class GoogleMapUtils {
    map: google.maps.Map;
    mapElement: any;

    constructor(mapElement: any) {
        this.mapElement = mapElement;
    }

    loadGoogleMap() {
        const mapOptions: google.maps.MapOptions = {
            zoom: 16,
            fullscreenControl: false,
            mapTypeControl: false,
            streetViewControl: false,
            clickableIcons: false
        };
        this.map = new google.maps.Map(this.mapElement, mapOptions);
    }

    addMarker(lat: number, lng: number, shop: Shop): google.maps.Marker {
        const lngLat = new google.maps.LatLng(lat, lng);
        var marker = new google.maps.Marker({position: lngLat, map: this.map, title: shop.shopBusinessName});

        marker.setMap(this.map);
        marker.setClickable(false);

        return marker;
    }

    addInfoWindow(marker: google.maps.Marker, shop: Shop) {
        var infowindow = new google.maps.InfoWindow({
            content: '<div id="content">'+
            '<div id="siteNotice">'+
            '</div>'+
            '<h3 id="thirdHeading" class="thirdHeading">' + shop.shopBusinessName + '</h3>'+
            '<div id="bodyContent">'+
            '<p>' + shop.shopAddress + '</p>'+
            '</div>'+
            '</div>'
        });

        var map = this.map;
        marker.setClickable(true);
        marker.addListener('click', function () {infowindow.open(map, marker)});
    }

    center(marker: google.maps.Marker) {
        this.map.setCenter(marker.getPosition());
    }

    setZoom(zoomLevel: number) {
        this.map.setZoom(zoomLevel);
    }
}