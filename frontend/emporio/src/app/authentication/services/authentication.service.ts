import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable, throwError } from 'rxjs';
import { map, tap, switchMap } from 'rxjs/operators';

import { environment } from '@src/environments/environment';
import { User } from '@src/app/authentication/models/user';
import { stringify } from 'querystring';
import { Router } from '@angular/router';
import { Shop, ShopAdapter } from '@src/app/shop/shop/shop';
import { Role } from '../models/role';

@Injectable({
    providedIn: 'root'
})
export class AuthenticationService {
    private currentShopSubject: BehaviorSubject<Shop>;
    private currentUserSubject: BehaviorSubject<User>;
    public currentUser: Observable<User>;
    public currentShop: Observable<Shop>;

    constructor(private http: HttpClient, private router: Router, private adapter: ShopAdapter) {
        this.currentUserSubject = new BehaviorSubject<User>(JSON.parse(localStorage.getItem('currentUser')));
        this.currentShopSubject = new BehaviorSubject<Shop>(JSON.parse(localStorage.getItem('currentUserShop')));
        this.currentUser = this.currentUserSubject.asObservable();
        this.currentShop = this.currentShopSubject.asObservable();
    }

    public get currentUserValue(): User {
        return this.currentUserSubject.value;
    }

    public get currentShopValue(): Shop {
        return this.currentShopSubject.value;
    }

    login(username: string, password: string) {
        return this.http.post<any>(`${environment.apiUrl}/auth/signin`, { username, password })
            .pipe(map(user => {
                // login successful if there's a jwt token in the response
                if (user && user.token) {
                    // store user details and jwt token in local storage to keep user logged in between page refreshes
                    localStorage.setItem('currentUser', JSON.stringify(user));
                    this.currentUserSubject.next(user);
                    if (user.role === Role.Dipendente || user.role === Role.Titolare) {
                        this.getShop(username).subscribe(
                            data => {
                                console.log(data);
                                localStorage.setItem('currentUserShop', JSON.stringify(data));
                                this.currentShopSubject.next(data);
                            }
                        );
                    }
                }

                return user;
            }));
    }

    private getShop(username: string) {
        return this.http.get(`${environment.apiUrl}/users/${username}/shops`, ).pipe(
            map(item => this.adapter.adapt(item))
          );
    }

    refresh() {
        const refresh = this.getRefreshToken();
        return this.http.post(`${environment.apiUrl}/auth/refresh`, { refresh }, { responseType: 'text' }).pipe(
            tap(newToken => {
                this.updateToken(newToken);
            }));
    }

    logout() {
        // remove user from local storage to log user out
        this.currentUserSubject.next(null);
        this.currentUser = null;
        this.removeToken();
        // ritorno alla home
        this.router.navigate(['/']);
    }

    getJwtToken() {
        const token = JSON.parse(localStorage.getItem('currentUser'))['token'];
        return (!token) ? null : token;
    }

    private getRefreshToken() {
        const refresh = JSON.parse(localStorage.getItem('currentUser'))['refresh'];
        return (!refresh) ? null : refresh;
    }

    private updateToken(token) {
        const old = JSON.parse(localStorage.getItem('currentUser'));
        old['token'] = token;
        const newUser = JSON.stringify(old);
        localStorage.setItem('currentUser', newUser);
        this.currentUserValue.token = token;
    }

    private removeToken() {
        localStorage.removeItem('currentUser');
        localStorage.removeItem('currentUserShop');
    }
}
