import { Injectable, Input } from '@angular/core';
import { Order } from '../order/Order';
import { Posto } from '../locker/Posto';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '@src/environments/environment';
import { Observable } from 'rxjs';
import { AuthenticationService } from '../authentication/services/authentication.service';
import { Delivery } from './delivery';
import { NotificationService } from '../notification.service';

@Injectable({
  providedIn: 'root'
})
export class DeliveryService {

  private selectedOrder: Order; // viene passato dalla lista degli ordini
  private selectedPlace: Posto; // viene passato dal componente del posto
  private fattorino: string;

  private deliveryUrl = `${environment.apiUrl}/delivery`;
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private httpService: HttpClient, private authService: AuthenticationService, private notificationService: NotificationService) { }

  getSelectedPlace(): Posto {

    return this.selectedPlace;

  }

  setSelectedPlace(selectedPlace: Posto) {

    this.selectedPlace = selectedPlace;

  }

  getSelectedOrder(): Order {

    return this.selectedOrder;

  }

  setSelectedOrder(selectedOrder: Order) {

    this.selectedOrder = selectedOrder;

  }

  createDelivery(): Observable<string> {

    if (typeof this.selectedPlace === 'undefined') {

      this.notificationService.warn('Posto non valido o non assegnato');
      return;

    }

    if (typeof this.selectedOrder === 'undefined') {
      this.notificationService.warn('Ordine non valido o non assegnato');
      return;
    }

    const body = {
      idOrdine: this.selectedOrder.id,
      fattorinoName: this.authService.currentUserValue.username,
      idPosto: this.selectedPlace.postoId
    };

    return this.httpService.post<string>(this.deliveryUrl, JSON.stringify(body), this.httpOptions);

  }

  getDeliveryList(): Observable<Delivery[]> {

    return this.httpService.get<Delivery[]>(this.deliveryUrl);

  }

  updateDeliveryStatusToDelivery(idDelivery: number): Observable<String> {

    return this.httpService.put<String>(this.deliveryUrl + '/' + idDelivery + '/states/consegnata', this.httpOptions);

  }

}
