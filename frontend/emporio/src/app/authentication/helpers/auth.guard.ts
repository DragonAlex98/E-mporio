import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';

import { AuthenticationService } from '@src/app/authentication/services/authentication.service';
import { NotificationService } from '@src/app/notification.service';

@Injectable({ providedIn: 'root' })
export class AuthGuard implements CanActivate {
    constructor(
        private router: Router,
        private authenticationService: AuthenticationService,
        private notificationService: NotificationService,
    ) { }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const currentUser = this.authenticationService.currentUserValue;
        if (currentUser) {
            // check if route is restricted by role
            console.log(route.data.roles);
            console.log(currentUser.role);
            console.log(route.data.roles.indexOf(currentUser.role));
            if (route.data.roles && route.data.roles.indexOf(currentUser.role) === -1) {
                this.notificationService.success('Utente ' + currentUser.username + ' con ruolo ' + currentUser.role + ' non abilitato');
                this.router.navigate(['/']);
                return false;
            }

            // authorised so return true
            return true;
        }

        this.notificationService.warn('Utente non loggato, impossibile accedere');
        this.router.navigate(['/']);
        return false;
    }
}
