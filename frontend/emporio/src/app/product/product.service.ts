import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders, HttpHeaderResponse } from '@angular/common/http';
import { Product, ProductCategory, ProductCategoryAdapter, ProductAdapter } from './product/product';
import { environment } from '@src/environments/environment';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root',
})
export class ProductService {

  constructor(private httpClient: HttpClient, private productAdapter: ProductAdapter,
    private productCategoryAdapter: ProductCategoryAdapter) { }

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json'})
  };

  httpPostOptions = {
    headers: this.httpOptions.headers,
    responseType: 'text' as 'json'
  };
  private productListUrlName = `${environment.apiUrl}/products/search`;
  private productsUrl = `${environment.apiUrl}/products`;

  searchProducts (term: string): Observable<Product[]> {
    return this.httpClient.get<Product[]>(`${this.productListUrlName}?nome=${term}`).pipe(
      map((data: any[]) => data.map(item => this.productAdapter.adapt(item.productDescription)))
    );
  }

  addProduct (form: any): Observable<string> {
    return this.httpClient.post<string>(this.productsUrl, form, this.httpPostOptions);
  }

  getCategories(): Observable<ProductCategory[]> {
    return this.httpClient.get<ProductCategory[]>(`${environment.apiUrl}/products/categories`, this.httpOptions).pipe(
      map((data: any[]) => data.map(elem => this.productCategoryAdapter.adapt(elem)))
    );
  }

}
