import { Component, OnInit } from '@angular/core';
import { Order } from '../Order';
import { OrderService } from '../order.service';
import { AuthenticationService } from '@src/app/authentication/services/authentication.service';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { switchMap } from 'rxjs/operators';
import { of } from 'rxjs';
import { OrderHistory } from '../OrderHistory';
import { NotificationService } from '@src/app/notification.service';

@Component({
  selector: 'app-order-history',
  templateUrl: './order-history.component.html',
  styleUrls: ['./order-history.component.css']
})
export class OrderHistoryComponent implements OnInit {
  orders: OrderHistory[];

  constructor(private route: ActivatedRoute, private router: Router,
    private orderService: OrderService, private authService: AuthenticationService,
    private notificationService: NotificationService) { }

  ngOnInit() {
    this.route.paramMap.pipe(
      switchMap((params: ParamMap) =>
        of(params.get('username'))
      )
    ).subscribe(username => {
      if (!(username === this.authService.currentUserValue.username)) {
        this.notificationService.warn('Impossibile accedere alla pagina di un altro utente!');
        this.router.navigateByUrl('/');
      }
    });

    this.orderService.getAllCustomerOrder(this.authService.currentUserValue.username).subscribe(
      data => this.orders = data
    );
  }

}
