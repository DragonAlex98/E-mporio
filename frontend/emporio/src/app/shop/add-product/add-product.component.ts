import { Component, OnInit, Input } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { Product, ProductCategory } from '@src/app/product/product/product';
import { ProductService } from '@src/app/product/product.service';
import { ShopService } from '../shop.service';

@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.css']
})
export class AddProductComponent implements OnInit {
  @Input() piva = '';
  productsDescriptions: Product[];
  selectedProductDescription: Product = new Product(null, null, new ProductCategory(null, ''));

  addProductForm = new FormGroup({
    productName: new FormControl(''),
    productPrice: new FormControl('')
  });

  constructor(private productService: ProductService, private shopService: ShopService) { }

  ngOnInit() {
    this.productService.getAllProductsDescriptions().subscribe(
      data => this.productsDescriptions = data,
      error => {
        if ([404].indexOf(error.status) !== -1) {
          alert(error.error.message);
        } else {
          alert('Errore di connessione!');
        }
      }
    );
  }

  onSubmit(formValue: any) {
    this.shopService.addProductToShop(this.piva, formValue).subscribe(
      data => alert(data),
      error => {
        if ([400, 404].indexOf(error.status) !== -1) {
          alert(error.error.message);
        } else {
          alert('Errore di connessione!');
        }
      }
    );
  }

  setSelected(product: Product) {
    this.selectedProductDescription = product;
  }

}
