import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Shop, ShopAdapter } from './shop/shop';
import { environment } from '@src/environments/environment';
import { map, tap } from 'rxjs/operators';
import { ProductAdapter, Product } from '../product/product/product';

@Injectable({
  providedIn: 'root'
})
export class ShopService {

  constructor(private httpClient: HttpClient, private shopAdapter: ShopAdapter, private productsAdapter: ProductAdapter) { }

  searchShops (term: string): Observable<Shop[]> {
    return this.httpClient.get<Shop[]>(`${environment.apiUrl}${environment.apiUri}/shops/search?ragSociale=${term}`).pipe(
      map((data: any[]) => data.map(item => this.shopAdapter.adapt(item)))
    );
  }

  addShop(term: Shop) {
    // TODO implementare POST
  }

  addEmployeeToShop(formData: any) {
    return this.httpClient.put(`${environment.apiUrl}${environment.apiUri}/shops/employees`, formData, {responseType: 'text'})
                          .pipe();
  }

  getShopInfos(username: string): Observable<Shop> {
    return this.httpClient.get(`${environment.apiUrl}${environment.apiUri}/users/${username}/shops`, ).pipe(
      map(item => this.shopAdapter.adapt(item))
    );
  }

  getShopCatalog(shopPIva: string): Observable<Product[]> {
    return this.httpClient.get(`${environment.apiUrl}${environment.apiUri}/shops/${shopPIva}/products`).pipe(
      map((data: any[]) => data.map(item => this.productsAdapter.adapt(item)))
    );
  }

}
