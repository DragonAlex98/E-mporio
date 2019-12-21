import {Role} from './role';

export class User {

    id: number;
    username: string;
    password: string;
    role: Role;
    token?: string;

    constructor(id: number, username: string, password: string, role: Role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

}
