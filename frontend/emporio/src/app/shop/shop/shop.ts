export class Shop {

    shopPIVA: string;
    shopAddress: string;
    shopBusinessName: string;
    shopCategory: number;
    shopHeadquarter: string;

    constructor(shopPIVA: string, shopAddress: string, shopBusinessName: string, shopCategory: number, shopHeadquarter: string) {
        this.shopPIVA = shopPIVA;
        this.shopAddress = shopAddress;
        this.shopBusinessName = shopBusinessName;
        this.shopCategory = shopCategory;
        this.shopHeadquarter = shopHeadquarter;
    }
}
