import { Component, OnInit, Input } from '@angular/core';
import { Order } from '@src/app/order/Order';
import { Posto } from '@src/app/locker/Posto';
import { DeliveryService } from '../delivery.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-delivery',
  templateUrl: './create-delivery.component.html',
  styleUrls: ['./create-delivery.component.css']
})
export class CreateDeliveryComponent implements OnInit {

  @Input() order: Order;
  posto: Posto;
  fattorino: string;

  constructor(private deliveryService: DeliveryService, private routerService: Router) {}

  ngOnInit() {}

  onClick() {
    // TODO Da vedere se si puo' fare meglio, come gestire il passaggio dati tra componenti non collegati
    this.deliveryService.setSelectedOrder(this.order);
    this.posto = this.deliveryService.getSelectedPlace();
    if (typeof this.posto === 'undefined') {return; }

    this.deliveryService.createDelivery().subscribe(
      (data) => {alert('Consegna creata con successo'); },
      (error) => {alert('Consegna non creata, gia presente o errore di connessione'); }
      );


      this.routerService.navigateByUrl('/home');


  }

}
