import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Locker } from './Locker';
import { environment } from '@src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class LockerService {


  apiUrl = `${environment.apiUrl}/locker`;

  constructor(private httpClient: HttpClient) { }

  getLockers(): Observable<Locker[]> {

     return this.httpClient.get<Locker[]>(this.apiUrl);

  }

  getEmtpyPlaces(idLocker): Observable<number> {

    return this.httpClient.get<number>(this.apiUrl + '/' + idLocker + '/postiliberi');

  }

}
