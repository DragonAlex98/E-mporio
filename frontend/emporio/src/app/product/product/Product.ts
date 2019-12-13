export class Product {

    productId: number;
    productName: string;
    productCategory: ProductCategory;
    productPrice: number;
    productQuantity: number;

    constructor(productId: number, productName: string, productCategory: ProductCategory, productPrice: number, productQuantity: number  ) {
      this.productId = productId;
      this.productName = productName;
      this.productCategory = productCategory;
      this.productPrice = productPrice;
      this.productQuantity = productQuantity;
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