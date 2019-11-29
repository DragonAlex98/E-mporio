export class Shop {

    shopPIva: string;
    shopRagioneSociale: string;
    shopCategory: number;
    shopSedeOperativa: string;

    constructor(shopPIva: string, shopRagioneSociale: string, shopCategory: number, shopSedeOperativa: string) {
        this.shopPIva = shopPIva;
        this.shopRagioneSociale = shopRagioneSociale;
        this.shopCategory = shopCategory;
        this.shopSedeOperativa = shopSedeOperativa;
    }
}
