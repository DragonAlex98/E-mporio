import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { environment } from '@src/environments/environment';
import { User } from '@src/app/authentication/models/user';

@Injectable({ providedIn: 'root' })
export class UserService {

    constructor(private http: HttpClient) { }

    getAll() {
        return this.http.get<User[]>(`${environment.apiUrl}/users`);
    }

    getById(id: number) {
        return this.http.get<User>(`${environment.apiUrl}/users/${id}`);
    }

    register(user: User) {
        return this.http.post(`${environment.apiUrl}/auth/signup`, user);
    }

    getUsers(username: string) {
        return this.http.get<User[]>(`${environment.apiUrl}/users/search`, {params: { username: username }});
    }

    toggleUser(username: string) {
        return this.http.put(`${environment.apiUrl}/users/${username}`, null);
    }
}

