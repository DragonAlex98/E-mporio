import { Order } from '../order/Order';
import { Posto } from '../locker/Posto';

export class Delivery {

    idConsegna: number;
    statoConsegna: string;
    ordine: Order;
    posto: Posto;

    constructor(idConsegna: number, statoConsegna: string, ordine: Order, posto: Posto){

        this.idConsegna = idConsegna;
        this.statoConsegna = statoConsegna;
        this.ordine = ordine;
        this.posto = posto;

    }

}