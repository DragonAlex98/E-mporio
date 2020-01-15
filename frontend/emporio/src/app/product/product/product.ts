import { Injectable } from "@angular/core";
import { Adapter } from "@src/app/adapter";

export class Product {

    productId: number;
    productName: string;
    productCategory: ProductCategory;

    constructor(productId: number, productName: string, productCategory: ProductCategory) {
      this.productId = productId;
      this.productName = productName;
      this.productCategory = productCategory;
    }
  }

  export class ProductCategory {
    categoryId: number;
    description: string;

    constructor(categoryId: number, description: string) {
      this.categoryId = categoryId;
      this.description = description;
    }
  }

@Injectable({
  providedIn: 'root'
})
export class ProductAdapter implements Adapter<Product> {
    adapt(item: any): Product {
        return new Product(item.productId, item.productName,
          new ProductCategory(item.productCategory.categoryId, item.productCategory.description));
    }
}

@Injectable({
  providedIn: 'root'
})
export class ProductCategoryAdapter implements Adapter<ProductCategory> {
    adapt(item: any): ProductCategory {
        return new ProductCategory(item.categoryId, item.description);
    }
}
