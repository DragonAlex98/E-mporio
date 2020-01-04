import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Shop } from './shop/shop';
import { environment } from '@src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ShopService {

  constructor(private httpClient: HttpClient) { }

  private shopAPIUrlName = `${environment.apiUrl}/shops/search`;

  searchShops (term: string): Observable<Shop[]> {
    return this.httpClient.get<Shop[]>(`${this.shopAPIUrlName}?ragSociale=${term}`);
  }

  addShop(term: Shop) {
    // TODO implementare POST
  }
}
