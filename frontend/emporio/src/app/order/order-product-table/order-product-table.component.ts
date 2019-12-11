import { AfterViewInit, Component, OnInit, ViewChild, Input, ChangeDetectorRef } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTable } from '@angular/material/table';
import { OrderProductTableDataSource, OrderProductTableItem } from '../order-product-table-datasource';

@Component({
  selector: 'app-order-product-table',
  templateUrl: './order-product-table.component.html',
  styleUrls: ['./order-product-table.component.css']
})
export class OrderProductTableComponent implements AfterViewInit, OnInit {
  @ViewChild(MatPaginator, {static: false}) paginator: MatPaginator;
  @ViewChild(MatSort, {static: false}) sort: MatSort;
  @ViewChild(MatTable, {static: false}) table: MatTable<OrderProductTableItem>;
  @Input() dataSource: OrderProductTableDataSource;

  /** Columns displayed in the table. Columns IDs can be added, removed, or reordered. */
  displayedColumns = ['id', 'name', 'category', 'quantity', 'actions'];

  constructor(private changeDetectorRefs: ChangeDetectorRef) { }

  ngOnInit() {
  }

  ngAfterViewInit() {
    this.dataSource.sort = this.sort;
    this.dataSource.paginator = this.paginator;
    this.table.dataSource = this.dataSource;
  }

  addProductToList(prodId: number, prodName: string, prodCat: string, prodqta: number){
    // this.dataSource.ad(prodId, prodName, prodCat, prodqta);
  }

  updateTable() {
    console.log('stonkls');
    this.changeDetectorRefs.detectChanges();
  }

  onEdit(row) {
  }

  onDelete(key) {}
}
