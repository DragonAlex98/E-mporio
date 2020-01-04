import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Product, ProductCategory } from './product/product';
import { environment } from '@src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class ProductService {

  constructor(private httpClient: HttpClient) { }

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };
  private productListUrlName = `${environment.apiUrl}/products/search`;
  private productsUrl = `${environment.apiUrl}/products`;

  searchProducts (term: string): Observable<Product[]> {
    return this.httpClient.get<Product[]>(`${this.productListUrlName}?nome=${term}`);
  }

  addProduct (product: Product): Observable<Product> {
    console.log(product);
    return this.httpClient.post<Product>(this.productsUrl, JSON.stringify(product), this.httpOptions);
  }

  getCategories(): Observable<ProductCategory[]> {
    return this.httpClient.get<ProductCategory[]>(`${environment.apiUrl}/categoryProduct`, this.httpOptions);
  }

}
