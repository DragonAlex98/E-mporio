import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';

import { ProductRoutingModule } from '@src/app/product/product-routing.module';
import { InsertProductComponent } from '@src/app/product/insert-product/insert-product.component';
import { InsertProductFormComponent } from '@src/app/product/insert-product-form/insert-product-form.component';
import { ProductComponent } from '@src/app/product/product/product.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { SearchProductPageComponent } from '@src/app/product/search-product-page/search-product-page.component';
import { MatButtonModule } from '@angular/material/button';
import {MatSelectModule} from '@angular/material/select';
import { MatIconModule } from '@angular/material/icon';
import {MatInputModule} from '@angular/material/input';
import { MatDividerModule } from '@angular/material/divider';
import {MatGridListModule} from '@angular/material/grid-list';


@NgModule({
  declarations: [
    InsertProductComponent,
    InsertProductFormComponent,
    ProductComponent,
    SearchProductPageComponent,
  ],
  imports: [
    CommonModule,
    HttpClientModule,
    ProductRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    MatSelectModule,
    MatButtonModule,
    MatIconModule,
    MatInputModule,
    MatDividerModule,
    MatGridListModule,
  ]
})
export class ProductModule { }
