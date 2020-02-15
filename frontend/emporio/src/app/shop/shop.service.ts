import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Shop, ShopAdapter, ShopCategory, ShopCategoryAdapter, Sale } from './shop/shop';
import { environment } from '@src/environments/environment';
import { map, tap } from 'rxjs/operators';
import { AuthenticationService } from '../authentication/services/authentication.service';
import { User } from '../authentication/models/user';
import { Product, ProductCategory, ProductAdapter } from '../product/product/product';
import { ClassificaShop } from './shop/classificaShop';
import { GoogleMapUtils } from '../googleMapUtils';

@Injectable({
  providedIn: 'root'
})
export class ShopService {
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json'})
  };

  constructor(private httpClient: HttpClient, private adapter: ShopAdapter, private auth: AuthenticationService,
    private productAdapter: ProductAdapter, private shopCatAdapter: ShopCategoryAdapter) { }

  searchShops (term: string): Observable<Shop[]> {
    return this.httpClient.get<Shop[]>(`${environment.apiUrl}/shops/search?ragSociale=${term}`).pipe(
      map((data: any[]) => data.map(item => this.adapter.adapt(item)))
    );
  }

  addShop(form: any): Observable<Shop>  {
    return this.httpClient.post<Shop>(`${environment.apiUrl}/shops`, form, this.httpOptions).pipe(
      map(item => this.adapter.adapt(item))
    );
  }

  getShopCategories(): Observable<ShopCategory[]> {
    return this.httpClient.get(`${environment.apiUrl}/shops/categories`, this.httpOptions).pipe(
      map((data: any[]) => data.map(item => this.shopCatAdapter.adapt(item)))
    );
  }

  addEmployeeToShop(formData: any) {
    return this.httpClient.put(`${environment.apiUrl}/shops/employees`, formData).pipe(
      map((item: any) => item.message)
    );
  }

  addMarketingManagerToShop(formData: any) {
    return this.httpClient.put(`${environment.apiUrl}/shops/managers`, formData).pipe(
      map((item: any) => item.message)
    );
  }

  getShopInfos(username: string): Observable<Shop> {
    return this.httpClient.get(`${environment.apiUrl}/users/${username}/shops`, ).pipe(
      map(item => this.adapter.adapt(item))
    );
  }

  getShopFromPIVA(piva: string): Observable<Shop> {
    return this.httpClient.get<Shop>(`${environment.apiUrl}/shops/${piva}`).pipe(
      map(item => this.adapter.adapt(item))
    );
  }

  deleteShop() {
    return this.httpClient.delete<string>(`${environment.apiUrl}/shops`).pipe();
  }

  deleteShopProduct(piva: string, productName: string): Observable<string> {
    return this.httpClient.delete(`${environment.apiUrl}/shops/${piva}/products?productName=${productName}`).pipe(
      map((res: string) => res)
    );
  }

  getShopCatalog(piva: string): Observable<Product[]> {
    return this.httpClient.get<Product[]>(`${environment.apiUrl}/shops/${piva}/products`).pipe(
      map((data: any[]) => data.map(item => this.productAdapter.adapt(item.productDescription)))
    );
  }

  addProductToShop(piva: string, formValue: any): Observable<string> {
    return this.httpClient.post<string>(`${environment.apiUrl}/shops/${piva}/products`, formValue, this.httpOptions).pipe(
      map((item: any) => item.message)
    );
  }
  
  getShopSalesList(piva: string): Observable<Sale[]> {
    return this.httpClient.get<Sale[]>(`${environment.apiUrl}/shops/${piva}/sales`).pipe(
      map((data: any[]) => data)
    );
  }

  getClassifica(): Observable<ClassificaShop[]> {
    return this.httpClient.get<ClassificaShop[]>(`${environment.apiUrl}/classifica`).pipe(
      map((data: any[]) => data)
    );
  }

  getAllExistingShops(): Observable<Shop[]> {
    return this.httpClient.get(`${environment.apiUrl}/shops`).pipe(
      map((data: any[]) => data.map(item => this.adapter.adapt(item)))
    );
  }
}
