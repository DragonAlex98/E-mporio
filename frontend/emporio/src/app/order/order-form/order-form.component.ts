import { Component, OnInit, ViewChild, AfterViewInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Product } from '@src/app/product/product/product';
import { OrderProductTableDataSource } from '../order-product-table-datasource';
import { User } from '@src/app/authentication/models/user';
import { Role } from '@src/app/authentication/models/role';
import { Order } from '../Order';
import { Shop } from '@src/app/shop/shop/shop';
import { OrderService } from '../order.service';
import { AuthenticationService } from '@src/app/authentication/services/authentication.service';
import { Router } from '@angular/router';
import { NotificationService } from '@src/app/notification.service';

@Component({
  selector: 'app-order-form',
  templateUrl: './order-form.component.html',
  styleUrls: ['./order-form.component.css']
})
export class OrderFormComponent implements OnInit, AfterViewInit {
  order: Order;
  customerFormGroup = new FormGroup({
    customerName: new FormControl('', Validators.required),
    customerCarPosition: new FormControl('', Validators.required)
  });
  dataSource: OrderProductTableDataSource;

  productList: Product[];

  constructor(private service: OrderService, private authService: AuthenticationService, private router: Router,
              private notificationService: NotificationService) { }

  ngOnInit() {
    this.dataSource = new OrderProductTableDataSource();
  }

  ngAfterViewInit(): void {
    var input = document.getElementById('searchTextField') as HTMLInputElement;
    var autocomplete = new google.maps.places.Autocomplete(input);
    google.maps.event.addListener(autocomplete, 'place_changed', function () {
      var place = autocomplete.getPlace();
      document.getElementById('cityAddr').textContent = place.formatted_address;
      document.getElementById('cityLat').textContent = place.geometry.location.lat().toString();
      document.getElementById('cityLng').textContent = place.geometry.location.lng().toString();
    });
  }

  addOrder() {
    this.customerFormGroup.get('customerCarPosition').patchValue(document.getElementById('cityAddr').textContent);

    if (this.customerFormGroup.invalid) { return; }

    this.service.addOrder(this.customerFormGroup.value.customerName,
      this.authService.currentUserValue.username,
      this.customerFormGroup.value.customerCarPosition,
      this.dataSource.getAsMap()).subscribe(
        data => {
          this.notificationService.success('Ordine Aggiunto');
          this.router.navigate(['/']);
        }
      );
  }

}
