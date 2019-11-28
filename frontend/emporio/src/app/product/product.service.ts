import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Product } from './product/product';

@Injectable({
  providedIn: 'root',
})
export class ProductService {

  constructor(private httpClient: HttpClient) { }

  private productListUrlName = 'http://localhost:8000/api/v1/products/search';

  searchProducts (term: string): Observable<Product[]> {
    return this.httpClient.get<Product[]>(`${this.productListUrlName}?nome=${term}`);
  }
}
