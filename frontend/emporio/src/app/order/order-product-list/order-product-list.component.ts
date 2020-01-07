import { Component, OnInit, ViewChild, Input } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { OrderProductTableItem, OrderProductTableDataSource } from '../order-product-table-datasource';
import { OrderProductTableComponent } from '../order-product-table/order-product-table.component';
import { Product, ProductCategory } from '@src/app/product/product/product';
import { OrderService } from '../order.service';
import { Shop } from '@src/app/shop/shop/shop';
import { AuthenticationService } from '@src/app/authentication/services/authentication.service';
import { ShopService } from '@src/app/shop/shop.service';

@Component({
  selector: 'app-order-product-list',
  templateUrl: './order-product-list.component.html',
  styleUrls: ['./order-product-list.component.css']
})
export class OrderProductListComponent implements OnInit {
  @ViewChild(OrderProductTableComponent, {static: false}) tableComponent: OrderProductTableComponent;
  productList: Product[];

  productListForm: FormGroup = new FormGroup({
    productSelect: new FormControl(''),
    prodQta: new FormControl(0),
  });
  @Input() dataSource: OrderProductTableDataSource;
  shop: Shop;

  constructor(private service: OrderService, private authService: AuthenticationService, private shopService: ShopService) { }

  ngOnInit() {
    this.shopService.getShopInfos(this.authService.currentUserValue.username).subscribe(
      data => {
        this.shopService.getShopCatalog(data.shopPIVA).subscribe(
          dataProd => this.productList = dataProd,
          error => this.productList = []
        );
        this.shop = data;
      },
      error => this.shop = null
    );
  }

  addProductToList(e, value: any) {
    console.log(value);
    if(value.productSelect === '') {
      return null;
    }
    this.dataSource.addData(value.productSelect, value.prodQta);
  }

}
