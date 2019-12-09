import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth.service';
import { InsertProductFormComponent } from '../product/insert-product-form/insert-product-form.component';
import { InsertProductComponent } from '../product/insert-product/insert-product.component';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';

@Component({
  selector: 'app-navbar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {
  events: string[] = [];
  opened: boolean;

  shouldRun = [/(^|\.)plnkr\.co$/, /(^|\.)stackblitz\.io$/].some(h => h.test(window.location.host));

  constructor(public auth: AuthService, public dialog: MatDialog) {  }

  ngOnInit() {
  }

  openDialog() {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.autoFocus = true;
    dialogConfig.width = '60%';
    const dialogRef = this.dialog.open(InsertProductComponent, dialogConfig);


    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
    });


  }

}
