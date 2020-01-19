import { Component, OnInit, Input } from '@angular/core';
import { Delivery } from '../delivery';
import { DeliveryService } from '../delivery.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-assign-delivery',
  templateUrl: './assign-delivery.component.html',
  styleUrls: ['./assign-delivery.component.css']
})
export class AssignDeliveryComponent implements OnInit {

  @Input()  delivery: Delivery;

  constructor(private deliveryService: DeliveryService, private router: Router) { }

  ngOnInit() {
  }

  onClick() {

    this.deliveryService.updateDeliveryStatusToDelivery(this.delivery.idConsegna).subscribe(

      (data) => {alert('Consegna assegnata correttamente al locker!'); },
      (error) => {alert(error.error.message); }

    );

    this.router.navigateByUrl('/delivery-list');

  }

}
