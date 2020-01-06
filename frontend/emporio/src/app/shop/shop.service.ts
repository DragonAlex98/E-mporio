import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Shop, ShopAdapter } from './shop/shop';
import { environment } from '@src/environments/environment';
import { map, tap } from 'rxjs/operators';
import { AuthenticationService } from '../authentication/services/authentication.service';
import { User } from '../authentication/models/user';
import { Product, ProductCategory } from '../product/product/product';

@Injectable({
  providedIn: 'root'
})
export class ShopService {

  constructor(private httpClient: HttpClient, private adapter: ShopAdapter, private auth: AuthenticationService) { }

  searchShops (term: string): Observable<Shop[]> {
    return this.httpClient.get<Shop[]>(`${environment.apiUrl}/shops/search?ragSociale=${term}`).pipe(
      map((data: any[]) => data.map(item => this.adapter.adapt(item)))
    );
  }

  addShop(term: Shop) {
    // TODO implementare POST
  }

  addEmployeeToShop(formData: any) {
    return this.httpClient.put(`${environment.apiUrl}/shops/employees`, formData, {responseType: 'text'})
                          .pipe();
  }

  getShopFromPIVA(piva: string): Observable<Shop> {
    return this.httpClient.get<Shop>(`${environment.apiUrl}/shops/${piva}`).pipe(
      map((data: Shop) => new Shop(data.shopPIVA, data.shopAddress, data.shopBusinessName, 1, data.shopHeadquarter))
    );
  }

  httpOptions = {
  };

  deleteShop(): Observable<string> {
    this.httpOptions = {headers: new HttpHeaders({ 'Authorization': 'Bearer ' + this.auth.currentUserValue.token })};
    console.log(this.httpOptions);
    return this.httpClient.delete(`${environment.apiUrl}/shops`, this.httpOptions).pipe(
      map((res: string) => res)
    );
  }

  getShopCatalog(piva: string): Observable<Product[]> {
    return this.httpClient.get<Product[]>(`${environment.apiUrl}/shops/${piva}/products`).pipe(
      map((data: any[]) => data.map(item => new Product(0, item.productName, new ProductCategory(1, "categoria"), 2, 3)))
    );
  }
}
