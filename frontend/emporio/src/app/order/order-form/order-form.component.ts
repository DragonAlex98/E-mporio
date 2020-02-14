import { Component, OnInit, ViewChild } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { Product } from '@src/app/product/product/product';
import { OrderProductTableDataSource } from '../order-product-table-datasource';
import { User } from '@src/app/authentication/models/user';
import { Role } from '@src/app/authentication/models/role';
import { Order } from '../Order';
import { Shop } from '@src/app/shop/shop/shop';
import { OrderService } from '../order.service';
import { AuthenticationService } from '@src/app/authentication/services/authentication.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-order-form',
  templateUrl: './order-form.component.html',
  styleUrls: ['./order-form.component.css']
})
export class OrderFormComponent implements OnInit {
  order: Order;
  customerFormGroup = new FormGroup({
    customerName: new FormControl(''),
    customerCarPosition: new FormControl('')
  });
  dataSource: OrderProductTableDataSource;

  productList: Product[];

  constructor(private service: OrderService, private authService: AuthenticationService, private router: Router) { }

  ngOnInit() {
    this.dataSource = new OrderProductTableDataSource();
  }

  addOrder() {
    this.service.addOrder(this.customerFormGroup.value.customerName,
      this.authService.currentUserValue.username,
      this.customerFormGroup.value.customerCarPosition,
      this.dataSource.getAsMap()).subscribe(
        data => {
          alert('Aggiunto');
          this.router.navigate(['/']);
        },
        error => {
          console.log(error);
          if ([400, 404].indexOf(error.status) !== -1) {
            alert(error.error.message);
          } else {
            alert('Errore di connessione!');
          }
        }
      );
  }

}
