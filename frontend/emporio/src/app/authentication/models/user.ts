import {Role} from './role';

export class User {

    id: number;
    username: string;
    role: Role;
    token?: string;
    refresh?: string;
    enabled?: boolean;

    constructor(id: number, username: string, role: Role) {
        this.id = id;
        this.username = username;
        this.role = role;
    }

}
