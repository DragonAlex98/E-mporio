import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { OrderProductTableItem } from './order-product-table-datasource';
import { map } from 'rxjs/operators';
import { OrderAdapter, Order } from './Order';
import { environment } from '@src/environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  constructor(private httpClient: HttpClient, private adapter: OrderAdapter) { }

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };
  apiOrderUrl = `${environment.apiUrl}/orders`;

  addOrder(customerUsername: string,
    employeeUsername: string,
    carPosition: string,
    productsList: OrderProductTableItem[]): Observable<Order> {
    const body = { customerUsername: customerUsername,
      employeeUsername: employeeUsername,
      carPosition: carPosition,
      productsList: productsList
    };
    console.log(this.apiOrderUrl);
    console.log(body);
    return this.httpClient.post<Order>(this.apiOrderUrl, JSON.stringify(body), this.httpOptions);
  }

  getOrdersToDelivery(): Observable<Order[]> {

    return this.httpClient.get<Order[]>(this.apiOrderUrl + '/state/not-assigned');

  }
}
