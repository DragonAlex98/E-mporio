import { DataSource } from '@angular/cdk/collections';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { map } from 'rxjs/operators';
import { Observable, of as observableOf, merge, BehaviorSubject } from 'rxjs';
import { Product, ProductCategory } from '../product/product/product';

// TODO: Replace this with your own data model type
export interface OrderProductTableItem {
  /* quantity: number;
  category: string;
  name: string;
  id: number; */
  product: Product;
  quantity: number;
}

// TODO: replace this with real data from your application
const EXAMPLE_DATA: OrderProductTableItem[] = [];

/**
 * Data source for the OrderProductTable view. This class should
 * encapsulate all logic for fetching and manipulating the displayed data
 * (including sorting, pagination, and filtering).
 */
export class OrderProductTableDataSource extends DataSource<OrderProductTableItem> {
  paginator: MatPaginator;
  sort: MatSort;
  dataStream = new BehaviorSubject<OrderProductTableItem[]>(EXAMPLE_DATA);

  set data(v: OrderProductTableItem[]) { this.dataStream.next(v); }
  get data(): OrderProductTableItem[] { return this.dataStream.value; }

  constructor() {
    super();
  }

  addData(product: Product, prodQta: number) {
    const copiedData = this.data.slice();
    copiedData.push({product: product, quantity: prodQta});
    this.data = copiedData;
  }

  getAsMap(): Map<string, number> {
    const m = new Map<string, number>();
    this.data.forEach(item => m.set(item.product.productName, item.quantity));
    return m;
  }

  /**
   * Connect this data source to the table. The table will only update when
   * the returned stream emits new items.
   * @returns A stream of the items to be rendered.
   */
  connect(): Observable<OrderProductTableItem[]> {
    // Combine everything that affects the rendered data into one update
    // stream for the data-table to consume.
    const dataMutations = [
      this.dataStream,
      this.paginator.page,
      this.sort.sortChange
    ];

    // Set the paginators length
    this.paginator.length = this.data.length;

    return merge(...dataMutations).pipe(map(() => {
      return this.getPagedData(this.getSortedData([...this.data]));
    }));
  }

  /**
   *  Called when the table is being destroyed. Use this function, to clean up
   * any open connections or free any held resources that were set up during connect.
   */
  disconnect() {}

  /**
   * Paginate the data (client-side). If you're using server-side pagination,
   * this would be replaced by requesting the appropriate data from the server.
   */
  private getPagedData(data: OrderProductTableItem[]) {
    const startIndex = this.paginator.pageIndex * this.paginator.pageSize;
    return data.splice(startIndex, this.paginator.pageSize);
  }

  /**
   * Sort the data (client-side). If you're using server-side sorting,
   * this would be replaced by requesting the appropriate data from the server.
   */
  private getSortedData(data: OrderProductTableItem[]) {
    if (!this.sort.active || this.sort.direction === '') {
      return data;
    }

    return data.sort((a, b) => {
      const isAsc = this.sort.direction === 'asc';
      switch (this.sort.active) {
        /* case 'name': return compare(a.name, b.name, isAsc);
        case 'id': return compare(+a.id, +b.id, isAsc); */
        default: return 0;
      }
    });
  }
}

/** Simple sort comparator for example ID/Name columns (for client-side sorting). */
function compare(a, b, isAsc) {
  return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
}
