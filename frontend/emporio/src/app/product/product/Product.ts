export class Product {

    productId: number;
    productName: string;
    productCategory: string;
    productPrice: number;
    productQuantity: number;

    constructor(productId: number, productName: string, productCategory: string, productPrice: number, productQuantity: number  ) {
      this.productId = productId;
      this.productName = productName;
      this.productCategory = productCategory;
      this.productPrice = productPrice;
      this.productQuantity = productQuantity;
    }
  }
