import { Component, OnInit, Input } from '@angular/core';
import { OrderHistory } from '../OrderHistory';
import { OrderService } from '../order.service';

@Component({
  selector: 'app-order-history-details',
  templateUrl: './order-history-details.component.html',
  styleUrls: ['./order-history-details.component.css']
})
export class OrderHistoryDetailsComponent implements OnInit {
  @Input() order: OrderHistory;
  isRitiraMerceEnabled: boolean;

  constructor(private orderHistoryService: OrderService) { }

  ngOnInit() {
  }

  ritiraMerce() {
    this.orderHistoryService.pickUpGoods(this.order.id).subscribe(
      data => this.order = data,
      error => {
        if ([404].indexOf(error.status) !== -1) {
          alert(error.error.message);
        } else {
          alert('Errore di connessione!');
        }
      }
    );
  }
}
