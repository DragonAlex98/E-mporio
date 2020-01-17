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
import { OrderModule } from '@src/app/order/order.module';
import { MaterialModule } from '@src/app/material/material.module';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import { AuthenticationModule } from '@src/app/authentication/authentication.module';
import { DeliveryModule } from '@src/app/delivery/delivery.module';
import { OrderService } from '@src/app/order/order.service';
import { MatStepperModule } from '@angular/material';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { JwtInterceptor } from '@src/app/authentication/helpers/jwt.interceptor';
import { DashboardHomeComponent } from '@src/app/dashboard/dashboard-home/dashboard-home.component';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    PageNotFoundComponent,
    NavBarComponent,
    FooterComponent,
    SearchBarComponent,
    DashboardHomeComponent,
  ],
  imports: [
    BrowserModule,
    ProductModule,
    ShopModule,
    AuthenticationModule,
    DeliveryModule,
    OrderModule,
    AppRoutingModule,
    FormsModule,
    MaterialModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    FlexLayoutModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatStepperModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: JwtInterceptor,
      multi: true
    },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
