import { Component, OnInit } from '@angular/core';
import { Shop } from '../shop/shop';
import { ShopService } from '../shop.service';

@Component({
  selector: 'app-shop-list',
  templateUrl: './shop-list.component.html',
  styleUrls: ['./shop-list.component.css']
})
export class ShopListComponent implements OnInit {

  shopList: Shop[];


  constructor(private shopService: ShopService) { }

  ngOnInit() {
  }

  loadList(term: string) {
    this.shopService.searchShops(term).subscribe();
  }

}
