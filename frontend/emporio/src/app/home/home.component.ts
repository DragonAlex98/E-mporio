import { Component, OnInit } from '@angular/core';
import { ClassificaShop } from '../shop/shop/classificaShop';
import { ShopService } from '../shop/shop.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit {
  title = 'E-mporio';

  classificaShopList: ClassificaShop[];

  constructor(private shopService: ShopService) { }

  ngOnInit() {
    this.shopService.getClassifica().subscribe(
      data => this.classificaShopList = data,
      error => {
          alert('Errore di connessione!');
      }
    );
  }
}
