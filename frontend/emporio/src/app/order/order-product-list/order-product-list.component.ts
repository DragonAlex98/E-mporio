import { Component, OnInit, ViewChild } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { OrderProductTableItem, OrderProductTableDataSource } from '../order-product-table-datasource';
import { OrderProductTableComponent } from '../order-product-table/order-product-table.component';

@Component({
  selector: 'app-order-product-list',
  templateUrl: './order-product-list.component.html',
  styleUrls: ['./order-product-list.component.css']
})
export class OrderProductListComponent implements OnInit {
  @ViewChild(OrderProductTableComponent, {static: false}) tableComponent: OrderProductTableComponent;
  productListForm: FormGroup = new FormGroup({
    prodId: new FormControl(0),
    prodName: new FormControl(''),
    prodCat: new FormControl(''),
    prodQta: new FormControl(0),
  });
  dataSource: OrderProductTableDataSource;

  constructor() { }

  ngOnInit() {
    this.dataSource = new OrderProductTableDataSource();
    console.log(this.dataSource);
  }

  addProductToList(e, value: any) {
    console.log(value.prodId);
    console.log(value.prodName);
    console.log(value.prodCat);
    console.log(value.prodQta);
    e.stopPropagation();
    e.preventDefault();
    this.dataSource.addData(this.productListForm.value.prodId, this.productListForm.value.prodName,
    this.productListForm.value.prodCat, this.productListForm.value.prodQta);
    this.tableComponent.updateTable();
  }

}
