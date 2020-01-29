import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';

import { AuthenticationService } from '@src/app/authentication/services/authentication.service';
import { Role } from '../models/role';

@Injectable({ providedIn: 'root' })
export class DashboardGuard implements CanActivate {
    constructor(private router: Router, private authenticationService: AuthenticationService) { }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const currentUser = this.authenticationService.currentUserValue;
        if (currentUser) {
            if (currentUser.role === Role.Admin || currentUser.role == Role.OperatoreSistema) {
                this.router.navigate(['/dashboard']);
                return false;
            }

            return true;
        }

        return true;
    }
}
