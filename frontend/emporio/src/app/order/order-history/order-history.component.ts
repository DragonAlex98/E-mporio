import { Component, OnInit } from '@angular/core';
import { Order } from '../Order';
import { OrderService } from '../order.service';
import { AuthenticationService } from '@src/app/authentication/services/authentication.service';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { switchMap } from 'rxjs/operators';
import { of } from 'rxjs';

@Component({
  selector: 'app-order-history',
  templateUrl: './order-history.component.html',
  styleUrls: ['./order-history.component.css']
})
export class OrderHistoryComponent implements OnInit {
  orders: Order[];

  constructor(private route: ActivatedRoute, private router: Router,
    private orderService: OrderService, private authService: AuthenticationService) { }

  ngOnInit() {
    this.route.paramMap.pipe(
      switchMap((params: ParamMap) =>
        of(params.get('username'))
      )
    ).subscribe(username => {
      if (!(username === this.authService.currentUserValue.username)) {
        alert('Impossibile accedere alla pagina di un altro utente!');
        this.router.navigateByUrl('/');
      }
    });

    this.orderService.getAllCustomerOrder(this.authService.currentUserValue.username).subscribe(
      data => this.orders = data,
      error => {
        if ([400, 404].indexOf(error.status) !== -1) {
          alert(error.error.message);
        } else {
          alert('Errore di connessione!');
        }
      }
    );
  }

}
