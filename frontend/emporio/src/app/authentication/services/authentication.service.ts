import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable, throwError } from 'rxjs';
import { map, tap, switchMap } from 'rxjs/operators';

import { environment } from '@src/environments/environment';
import { User } from '@src/app/authentication/models/user';
import { stringify } from 'querystring';
import { Router } from '@angular/router';

@Injectable({
    providedIn: 'root'
})
export class AuthenticationService {

    private currentUserSubject: BehaviorSubject<User>;
    public currentUser: Observable<User>;

    constructor(private http: HttpClient, private router: Router) {
        this.currentUserSubject = new BehaviorSubject<User>(JSON.parse(localStorage.getItem('currentUser')));
        this.currentUser = this.currentUserSubject.asObservable();
    }

    public get currentUserValue(): User {
        return this.currentUserSubject.value;
    }

    login(username: string, password: string) {
        return this.http.post<any>(`${environment.apiUrl}/auth/signin`, { username, password })
            .pipe(map(user => {
                // login successful if there's a jwt token in the response
                if (user && user.token) {
                    // store user details and jwt token in local storage to keep user logged in between page refreshes
                    localStorage.setItem('currentUser', JSON.stringify(user));
                    this.currentUserSubject.next(user);
                }

                return user;
            }));
    }

    refresh() {
        var refresh = this.getRefreshToken();
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
        var token = JSON.parse(localStorage.getItem('currentUser'))['token'];
        return (!token) ? null : token;
    }

    private getRefreshToken() {
        var refresh = JSON.parse(localStorage.getItem('currentUser'))['refresh'];
        return (!refresh) ? null : refresh;
    }

    private updateToken(token) {
        var old = JSON.parse(localStorage.getItem('currentUser'));
        old['token'] = token;
        var newUser = JSON.stringify(old);
        localStorage.setItem('currentUser', newUser);
        this.currentUserValue.token = token;
    }

    private removeToken() {
        localStorage.removeItem('currentUser');
    }
}
