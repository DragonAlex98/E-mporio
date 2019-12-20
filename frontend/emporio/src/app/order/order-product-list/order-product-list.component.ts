import { Component, OnInit, ViewChild, Input } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { OrderProductTableItem, OrderProductTableDataSource } from '../order-product-table-datasource';
import { OrderProductTableComponent } from '../order-product-table/order-product-table.component';
import { Product, ProductCategory } from '@src/app/product/product/product';

@Component({
  selector: 'app-order-product-list',
  templateUrl: './order-product-list.component.html',
  styleUrls: ['./order-product-list.component.css']
})
export class OrderProductListComponent implements OnInit {
  @ViewChild(OrderProductTableComponent, {static: false}) tableComponent: OrderProductTableComponent;
  productList: Product[] = [];

  productListForm: FormGroup = new FormGroup({
    productSelect: new FormControl(''),
    prodQta: new FormControl(0),
  });
  @Input() dataSource: OrderProductTableDataSource;

  constructor() { }

  ngOnInit() {
    const p1: Product = new Product(1, 'prosciutto cotto', new ProductCategory(1, 'salamirko'), 11, 7);
    const p2: Product = new Product(2, 'salamalekum', new ProductCategory(2, 'sdorbs'), 18, 3);
    this.productList.push(p1, p2);
    console.log(this.dataSource);
  }

  addProductToList(e, value: any) {
    console.log(value);
    if(value.productSelect === '') {
      return null;
    }
    this.dataSource.addData(value.productSelect, value.prodQta);
  }

}
