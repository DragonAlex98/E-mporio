import { Component, OnInit, Input } from '@angular/core';
import { Delivery } from '../delivery';
import { DeliveryService } from '../delivery.service';
import { Router } from '@angular/router';
import { NotificationService } from '@src/app/notification.service';

@Component({
  selector: 'app-assign-delivery',
  templateUrl: './assign-delivery.component.html',
  styleUrls: ['./assign-delivery.component.css']
})
export class AssignDeliveryComponent implements OnInit {

  @Input()  delivery: Delivery;

  constructor(private deliveryService: DeliveryService, private router: Router, private notificationService: NotificationService) { }

  ngOnInit() {
  }

  onClick() {

    this.deliveryService.updateDeliveryStatusToDelivery(this.delivery.idConsegna).subscribe(
      (data) => {this.notificationService.success('Consegna assegnata correttamente al locker!'); }
    );

    this.router.navigateByUrl('/delivery-list');

  }

}
