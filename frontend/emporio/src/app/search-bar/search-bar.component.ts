import { Component, OnInit } from '@angular/core';
import {FormGroup, FormControl} from '@angular/forms';
import {ProductService} from '../product/product.service';
import { Product } from '../product/product/product';
import { ShopService } from '../shop/shop.service';
import { Shop } from '../shop/shop/shop';

@Component({
  selector: 'app-search-bar',
  templateUrl: './search-bar.component.html',
  styleUrls: ['./search-bar.component.css'],
})
export class SearchBarComponent implements OnInit {

  showProducts = false;
  showShops = false;
  shopList: Shop[];
  productList: Product[];
  searchTypeStates = ['', 'Prodotti', 'Attivita'];
  searchBox = new FormGroup({
    searchInput : new FormControl(''),
    searchSelectorState : new FormControl({value : this.searchTypeStates[0]})
  });
  searchTerm = '';
  searchSelectorState = '';

  onSubmit(searchValue) {
    this.searchTerm = searchValue.searchInput;
    this.searchSelectorState = searchValue.searchSelectorState;
    if (this.searchSelectorState === '') {
    } else {
      this.search(this.searchTerm);
    }
    if ( this.searchSelectorState === 'Prodotti') {
      this.showProducts = true;
      this.showShops = false;
    } else if ( this.searchSelectorState === 'Attivita') {
      this.showProducts = false;
      this.showShops = true;
    }
  }

  constructor(private productService: ProductService, private shopService: ShopService) { }

  ngOnInit() {
  }

  search(term: string) {
    if ( this.searchSelectorState === 'Prodotti') {
      this.productService.searchProducts(term)
      .subscribe((productListData) => this.productList = productListData.map(
        item => {
          return new Product(
            item.productId,
            item.productName,
            item.productCategory,
            item.productPrice,
            item.productQuantity
          );
        }
      ));
    } else if (this.searchSelectorState === 'Attivita') {
      this.shopService.searchShops(term).subscribe((shopListData) => this.shopList = shopListData.map(
        item => {
          return new Shop(
            item.shopPIVA,
            item.shopAddress,
            item.shopBusinessName,
            item.shopCategory,
            item.shopHeadquarter
          );
        }
      ));
    }
  }
}