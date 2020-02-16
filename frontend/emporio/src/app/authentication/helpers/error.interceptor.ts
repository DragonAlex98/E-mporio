import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

import { AuthenticationService } from '@src/app/authentication/services/authentication.service';
import { NotificationService } from '@src/app/notification.service';

@Injectable()
export class ErrorInterceptor implements HttpInterceptor {
    constructor(private authenticationService: AuthenticationService, private notificationService: NotificationService) { }

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        return next.handle(request).pipe(catchError(err => {
            if (!err) {
                this.notificationService.warn("Errore di sistema");
            } else if (err.error.message) {
                this.notificationService.warn(err.error.message);
            } else if (err.error) {
                this.notificationService.warn(err.error);
            } else if (err) {
                this.notificationService.warn(err);
            }
            return throwError(err);
        }));
    }
}
