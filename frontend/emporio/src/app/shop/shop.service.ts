import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Shop } from './shop/shop';

@Injectable({
  providedIn: 'root'
})
export class ShopService {

  constructor(private httpClient: HttpClient) { }

  private shopAPIUrlName = 'http://localhost:8000/api/v1/shops/search';

  searchShops (term: string): Observable<Shop[]> {
    return this.httpClient.get<Shop[]>(this.shopAPIUrlName + '?ragSociale=' + term);
  }

  addShop(term: Shop) {
    // TODO implementare POST
  }
}
