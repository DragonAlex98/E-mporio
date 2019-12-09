import { DataSource } from '@angular/cdk/collections';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { map } from 'rxjs/operators';
import { Observable, of as observableOf, merge } from 'rxjs';

// TODO: Replace this with your own data model type
export interface OrderProductTableItem {
  quantity: number;
  category: string;
  name: string;
  id: number;
}

// TODO: replace this with real data from your application
const EXAMPLE_DATA: OrderProductTableItem[] = [
  {id: 1, name: 'Hydrogen', category: 'salume', quantity: 9},
  {id: 2, name: 'Helium', category: 'salume', quantity: 8},
  {id: 3, name: 'Lithium', category: 'salume', quantity: 7},
  {id: 4, name: 'Beryllium', category: 'salume', quantity: 5},
  {id: 5, name: 'Boron', category: 'salume', quantity: 3},
  {id: 6, name: 'Carbon', category: 'salume', quantity: 7},
  {id: 7, name: 'Nitrogen', category: 'salume', quantity: 3},
  {id: 8, name: 'Oxygen', category: 'salume', quantity: 7},
  {id: 9, name: 'Fluorine', category: 'salume', quantity: 5},
  {id: 10, name: 'Neon', category: 'salume', quantity: 5},
  {id: 11, name: 'Sodium', category: 'salume', quantity: 4},
  {id: 12, name: 'Magnesium', category: 'salume', quantity: 6},
  {id: 13, name: 'Aluminum', category: 'salume', quantity: 7},
  {id: 14, name: 'Silicon', category: 'salume', quantity: 2},
  {id: 15, name: 'Phosphorus', category: 'salume', quantity: 4},
  {id: 16, name: 'Sulfur', category: 'salume', quantity: 1},
  {id: 17, name: 'Chlorine', category: 'salume', quantity: 6},
  {id: 18, name: 'Argon', category: 'salume', quantity: 4},
  {id: 19, name: 'Potassium', category: 'salume', quantity: 7},
  {id: 20, name: 'Calcium', category: 'salume', quantity: 3},
];

/**
 * Data source for the OrderProductTable view. This class should
 * encapsulate all logic for fetching and manipulating the displayed data
 * (including sorting, pagination, and filtering).
 */
export class OrderProductTableDataSource extends DataSource<OrderProductTableItem> {
  data: OrderProductTableItem[] = EXAMPLE_DATA;
  paginator: MatPaginator;
  sort: MatSort;

  constructor() {
    super();
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
      observableOf(this.data),
      this.paginator.page,
      this.sort.sortChange
    ];

    return merge(...dataMutations).pipe(map(() => {
      return this.getPagedData(this.getSortedData([...this.data]));
    }));
  }

  addElementToDatasource(prodId: number, prodName: string, prodCat: string, prodqta: number) {
    const newOrder = {id: prodId, name: prodName, category: prodCat, quantity: prodqta};
    this.data.push(newOrder);
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
        case 'name': return compare(a.name, b.name, isAsc);
        case 'id': return compare(+a.id, +b.id, isAsc);
        default: return 0;
      }
    });
  }
}

/** Simple sort comparator for example ID/Name columns (for client-side sorting). */
function compare(a, b, isAsc) {
  return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
}
