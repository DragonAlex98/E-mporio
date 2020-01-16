import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor, HttpErrorResponse } from '@angular/common/http';
import { Observable, BehaviorSubject, throwError } from 'rxjs';

import { environment } from '@src/environments/environment';
import { AuthenticationService } from '@src/app/authentication/services/authentication.service';
import { catchError, switchMap, filter, take, tap, map } from 'rxjs/operators';
import { error } from 'util';

@Injectable({
    providedIn: 'root'
})
export class JwtInterceptor implements HttpInterceptor {
    constructor(private authenticationService: AuthenticationService) { }

    addAuthenticationToken(request: HttpRequest<any>, token: string) {
        if (!token) {
            return request;
        }

        return request.clone({
            setHeaders: {
                Authorization: `Bearer ${token}`
            }
        });
    }

    private refreshTokenInProgress = false; //funge da semaforo
    private refreshTokenSubject: BehaviorSubject<any> = new BehaviorSubject<any>(null);

    private handleUnauthorizedError(request: HttpRequest<any>, next: HttpHandler) {
        if (!this.refreshTokenInProgress) {
            // se non è stata ancora fatta richiesta per un nuovo token, la faccio
            this.refreshTokenInProgress = true;
            this.refreshTokenSubject.next(null);

            console.log("Vecchio token: " + this.authenticationService.currentUserValue.token);

            return this.authenticationService.refresh().pipe(
                switchMap(newToken => {
                    // sia che ho un errore, sia che il refresh token
                    // non è scaduto, sblocco le richieste pendenti
                    if(!newToken || newToken.length <= 0) {
                        this.authenticationService.logout();
                        this.refreshTokenInProgress = false;
                        this.refreshTokenSubject.next(newToken);
                        return throwError(error);
                    }
                    this.refreshTokenInProgress = false;
                    this.refreshTokenSubject.next(newToken);
                    return next.handle(this.addAuthenticationToken(request, newToken));
              }));
        } else {
            // altrimenti mi metto in attesa
            return this.refreshTokenSubject.pipe(
                filter(token => token != null), // attesa
                take(1), // chiudo lo stream una volta risolta la richiesta
                switchMap(token => {
                    // risolvo le richieste pendenti
                    return next.handle(this.addAuthenticationToken(request, token));
                }));
        }
    }

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        console.log('Intercettato');
        
        const currentUser = this.authenticationService.currentUserValue;
        const isLoggedIn = currentUser && currentUser.token;
        const isApiUrl = request.url.startsWith(environment.apiUrl);
        // devo rimuovere l'authorization header per refreshare il token,
        // perchè se il token è scaduto, la richiesta viene bloccata lato backend
        // dal JwtTokenAuthenticationFilter
        if (isLoggedIn && isApiUrl && !request.url.endsWith('/auth/refresh')) {
            request = this.addAuthenticationToken(request, currentUser.token);
        }

        // effettua la richiesta
        return next.handle(request).pipe(catchError(error => {
            if (error instanceof HttpErrorResponse && error.status === 401) {
                // se ricevo errore 401 UNAUTHORIZED, lo gestisco
                return this.handleUnauthorizedError(request, next);
            } else {
                return throwError(error);
            }
        }));
    }
}
