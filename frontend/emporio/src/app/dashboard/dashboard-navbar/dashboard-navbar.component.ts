import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '@src/app/authentication/services/authentication.service';
import { AuthenticationChecks } from '@src/app/AuthenticationChecks';

@Component({
  selector: 'app-dashboard-navbar',
  templateUrl: './dashboard-navbar.component.html',
  styleUrls: ['./dashboard-navbar.component.css']
})
export class DashboardNavbarComponent implements OnInit {
  events: string[] = [];
  opened: boolean;

  constructor(private authService: AuthenticationService, private authChecks: AuthenticationChecks) { }

  isAdmin(): boolean {
    return this.authChecks.isAdmin();
  }

  isOperatore(): boolean {
    return this.authChecks.isOperatore();
  }

  canAddLocker(): boolean {
    return this.authChecks.canInsertLocker();
  }

  ngOnInit() {
  }

  getUsername() {
    return this.authService.currentUserValue.username;
  }

  logout() {
    this.authService.logout();
  }

}
