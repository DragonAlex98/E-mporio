import { Component, OnInit, AfterViewInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ShopService } from '../shop.service';
import { ShopCategory } from '../shop/shop';

@Component({
  selector: 'app-insert-shop-form',
  templateUrl: './insert-shop-form.component.html',
  styleUrls: ['./insert-shop-form.component.css']
})
export class InsertShopFormComponent implements OnInit, AfterViewInit {
  categories: ShopCategory[];

  shopForm: FormGroup = new FormGroup({
    shopPIVA: new FormControl('', Validators.required),
    shopAddress: new FormControl('', Validators.required),
    shopBusinessName: new FormControl('', Validators.required),
    shopCategoryDescription: new FormControl('', Validators.required),
    shopHeadquarter: new FormControl('', Validators.required),
    shopLatitude: new FormControl('', Validators.required),
    shopLongitude: new FormControl('', Validators.required)
  });

  constructor(private shopService: ShopService) { }

  ngOnInit() {
    this.shopService.getShopCategories().subscribe(
      data => this.categories = data,
      error => alert('Errore di connessione!')
    );
  }

  ngAfterViewInit(): void {
    var input = document.getElementById('searchTextField') as HTMLInputElement;
    var autocomplete = new google.maps.places.Autocomplete(input);
    google.maps.event.addListener(autocomplete, 'place_changed', function () {
      var place = autocomplete.getPlace();
      document.getElementById('cityAddr').textContent = place.formatted_address;
      document.getElementById('city2').textContent = place.name;
      document.getElementById('cityLat').textContent = place.geometry.location.lat().toString();
      document.getElementById('cityLng').textContent = place.geometry.location.lng().toString();
    });
  }

  onSubmit() {
    this.shopForm.get('shopAddress').patchValue(document.getElementById('cityAddr').textContent);
    this.shopForm.get('shopLatitude').patchValue(document.getElementById('cityLat').textContent);
    this.shopForm.get('shopLongitude').patchValue(document.getElementById('cityLng').textContent);
    console.log(this.shopForm.value);
    this.shopService.addShop(this.shopForm.value).subscribe(
      data => alert(data),
      error => {
        if ([400].indexOf(error.status) !== -1) {
          alert(error.error.message);
        } else {
          alert('Errore di connessione!');
        }
      }
    );
  }

}
