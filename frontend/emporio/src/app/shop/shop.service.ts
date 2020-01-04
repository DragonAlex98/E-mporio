import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Shop, ShopAdapter } from './shop/shop';
import { environment } from '@src/environments/environment';
import { map, tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ShopService {

  constructor(private httpClient: HttpClient, private adapter: ShopAdapter) { }

  searchShops (term: string): Observable<Shop[]> {
    return this.httpClient.get<Shop[]>(`${environment.apiUrl}${environment.apiUri}/shops/search?ragSociale=${term}`).pipe(
      map((data: any[]) => data.map(item => this.adapter.adapt(item)))
    );
  }

  addShop(term: Shop) {
    // TODO implementare POST
  }

  addEmployeeToShop(formData: any) {
    return this.httpClient.put(`${environment.apiUrl}${environment.apiUri}/shops/employees`, formData, {responseType: 'text'})
                          .pipe();
  }

}
