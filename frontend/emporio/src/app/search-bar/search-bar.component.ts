import { Component, OnInit } from '@angular/core';
import {FormGroup, FormControl} from '@angular/forms';
import {ProductService} from '../product/product.service';
import { Product } from '../product/product/product';


@Component({
  selector: 'app-search-bar',
  templateUrl: './search-bar.component.html',
  styleUrls: ['./search-bar.component.css'],
})
export class SearchBarComponent implements OnInit {

  show = false;
  productList: Product[];
  searchTypeStates = ['Prodotti', 'Attivita'];
  searchBox = new FormGroup({
    searchInput : new FormControl(''),
    searchSelectorState : new FormControl({value : this.searchTypeStates[0]})
  });
  searchTerm = '';
  searchSelectorState = '';

  onSubmit(searchValue) {
    this.searchTerm = searchValue.searchInput;
    this.searchSelectorState = searchValue.searchSelectorState;
    this.search(this.searchTerm);
    this.show = true;
  }

  constructor(private productService: ProductService) { }

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
    }
  }
}
