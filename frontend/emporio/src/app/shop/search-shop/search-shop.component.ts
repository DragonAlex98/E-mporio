import { Component, OnInit, Input } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { ShopService } from '../shop.service';
import { Shop } from '../shop/shop';

@Component({
  selector: 'app-search-shop',
  templateUrl: './search-shop.component.html',
  styleUrls: ['./search-shop.component.css']
})
export class SearchShopComponent implements OnInit {
  @Input() searchTerm = '';
  shopList: Shop[];

  constructor(private route: ActivatedRoute, private router: Router, private service: ShopService) { }

  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      this.searchTerm = params.text;
      console.log(this.searchTerm);
    });
    this.service.searchShops(this.searchTerm).subscribe(
      data => this.shopList = data
    );
  }

}
