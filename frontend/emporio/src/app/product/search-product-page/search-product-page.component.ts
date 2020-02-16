import { Component, OnInit, Input } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { ProductService } from '../product.service';
import { Product, ProductCategory } from '../product/product';

@Component({
  selector: 'app-search-product-page',
  templateUrl: './search-product-page.component.html',
  styleUrls: ['./search-product-page.component.css']
})
export class SearchProductPageComponent implements OnInit {
  @Input() searchTerm = '';
  productList: Product[];

  constructor(private route: ActivatedRoute, private router: Router, private service: ProductService) { }

  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      this.searchTerm = params.text;
    });
    this.service.searchProducts(this.searchTerm).subscribe(
      data => this.productList = data
    );
  }

}
