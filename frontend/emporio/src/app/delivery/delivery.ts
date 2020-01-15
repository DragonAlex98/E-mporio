import { Order } from '../order/Order';
import { Posto } from '../locker/Posto';

export class Delivery {

    idConsegna: number;
    statoConsegna: string;
    ordine: Order;
    posto: Posto;

}