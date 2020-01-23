import { Component, OnInit } from '@angular/core';
import { UserService } from '@src/app/authentication/services/user.service';
import { User } from '@src/app/authentication/models/user';
import { Observable } from 'rxjs';
import { AuthenticationChecks } from '@src/app/AuthenticationChecks';

@Component({
  selector: 'app-dashboard-users',
  templateUrl: './dashboard-users.component.html',
  styleUrls: ['./dashboard-users.component.css']
})
export class DashboardUsersComponent implements OnInit {

  searchText : string;
  users : User[];

  constructor(private userService: UserService, private authChecks: AuthenticationChecks) { }

  getRoleName(role) {
    return role.name;
  }

  ngOnInit() {
  }

  updateSearch() {
    this.search();
  }

  showToggle(): boolean {
    return this.authChecks.isAdmin();
  }

  search() {
    if (!this.searchText || this.searchText.length == 0) {
      this.users = null;
      return;
    }

    this.userService.getUsers(this.searchText).subscribe(
      (data) => {
        this.users = data;
      },
      (error) => {
        console.log(error.error.message);
      }
    );
  }

  toggleUser(username: string) {
    this.userService.toggleUser(username).subscribe(
      () => {
        const user = this.users.find(u => u.username === username);
        user.enabled = !user.enabled;
      },
      (error) => {
        console.log(error.error.message);
      }
    );
  }
}
