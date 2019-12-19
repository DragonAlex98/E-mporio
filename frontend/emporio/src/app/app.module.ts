import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from '@src/app/app-routing.module';
import { AppComponent } from '@src/app/app.component';
import { HomeComponent } from '@src/app/home/home.component';
import { ProductModule } from '@src/app/product/product.module';
import { PageNotFoundComponent } from '@src/app/page-not-found/page-not-found.component';
import { SearchBarComponent } from '@src/app/search-bar/search-bar.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ShopModule } from '@src/app/shop/shop.module';
import { NavBarComponent } from '@src/app/nav-bar/nav-bar.component';
import { FooterComponent } from '@src/app/footer/footer.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FlexLayoutModule } from '@angular/flex-layout';
import { OrderModule } from './order/order.module';
import { MaterialModule } from './material/material.module';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import { AuthenticationModule } from './authentication/authentication.module';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    PageNotFoundComponent,
    NavBarComponent,
    FooterComponent,
    SearchBarComponent,
  ],
  imports: [
    BrowserModule,
    ProductModule,
    ShopModule,
    AuthenticationModule,
    AppRoutingModule,
    FormsModule,
    MaterialModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    FlexLayoutModule,
    OrderModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
