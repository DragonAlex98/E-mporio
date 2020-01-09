import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Locker } from './Locker';
import { environment } from '@src/environments/environment';
import { Posto } from './Posto';

@Injectable({
  providedIn: 'root'
})
export class LockerService {


  apiUrl = `${environment.apiUrl}/locker`;

  constructor(private httpClient: HttpClient) { }

  getLockers(): Observable<Locker[]> {

     return this.httpClient.get<Locker[]>(this.apiUrl);

  }

  getEmtpyPlaces(idLocker): Observable<Posto[]> {

    return this.httpClient.get<Posto[]>(this.apiUrl + '/' + idLocker + '/postiliberi');

  }

}
