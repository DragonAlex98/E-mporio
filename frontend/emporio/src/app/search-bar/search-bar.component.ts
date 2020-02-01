import { Component, OnInit } from '@angular/core';
import {FormGroup, FormControl} from '@angular/forms';
import {ProductService} from '../product/product.service';
import { Product } from '../product/product/product';
import { ShopService } from '../shop/shop.service';
import { Shop } from '../shop/shop/shop';

import { Router, ActivatedRoute, ParamMap } from '@angular/router';

@Component({
  selector: 'app-search-bar',
  templateUrl: './search-bar.component.html',
  styleUrls: ['./search-bar.component.css'],
})
export class SearchBarComponent implements OnInit {


  searchTypeStates = ['Prodotti', 'Attivita'];
  searchBox = new FormGroup({
    searchInput : new FormControl(''),
    searchSelectorState : new FormControl({value : this.searchTypeStates[0]})
  });

  search(searchValue) {
    console.log(searchValue.searchSelectorState);
    console.log(searchValue.searchSelectorState === 'Prodotti');
    if ( searchValue.searchSelectorState === 'Prodotti') {
      this.router.navigate(['/cerca-prodotto'], { queryParams: {text: searchValue.searchInput}});
    } else if (searchValue.searchSelectorState === 'Attivita') {
      this.router.navigate(['/cerca-attivita'], { queryParams: {text: searchValue.searchInput}});
    }
  }

  constructor(private productService: ProductService, private shopService: ShopService, private route: ActivatedRoute,
     private router: Router) { }

  ngOnInit() {
    this.searchBox.patchValue({
      searchSelectorState: 'Prodotti',
    });
  }

}
