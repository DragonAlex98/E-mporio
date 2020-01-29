import { Component, OnInit } from '@angular/core';
import { Sale } from '../shop/shop';
import { ShopService } from '../shop.service';
import { ActivatedRoute, Router, ParamMap } from '@angular/router';
import { switchMap } from 'rxjs/operators';
import { of } from 'rxjs';

@Component({
  selector: 'app-shop-sales',
  templateUrl: './shop-sales.component.html',
  styleUrls: ['./shop-sales.component.css']
})
export class ShopSalesComponent implements OnInit {
  piva = '';
  salesList: Sale[];

  constructor(private route: ActivatedRoute, private router: Router, private shopService: ShopService) { }

  ngOnInit() {
    this.route.paramMap.pipe(
      switchMap((params: ParamMap) =>
        of(params.get('piva'))
      )
    ).subscribe(piva => {
      this.piva = piva;
    });
    this.getShopSalesList();
  }

  getShopSalesList() {
    if (!this.piva || this.piva.length == 0) return;

    this.shopService.getShopSalesList(this.piva).subscribe(
      (data) => {
        this.salesList = data;
      },
      (error) => {
        
      }
    );
  }
}
