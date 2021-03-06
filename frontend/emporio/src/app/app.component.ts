import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from './authentication/services/authentication.service';
import { AuthenticationChecks } from './AuthenticationChecks';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent implements OnInit {

  constructor(private authService: AuthenticationService) {}

  private checkExpiredToken() {
    if (!this.authService.currentUserValue) return;
    
    this.authService.refresh().subscribe(
      token => {
          if(!token || token.length <= 0) {
              this.authService.logout();
          }
        }
    );
  }

  ngOnInit(): void {
    this.checkExpiredToken();
  }
}
