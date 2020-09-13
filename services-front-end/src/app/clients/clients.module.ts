import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { ClientsRoutingModule } from './clients-routing.module';
import { ClientsFormComponent } from './clients-form/clients-form.component';
import { ClientsListComponent } from './clients-list/clients-list.component';


@NgModule({
  declarations: [ClientsFormComponent, ClientsListComponent],
  imports: [
    CommonModule,
    ClientsRoutingModule,
    FormsModule
  ],
  exports: [
    ClientsFormComponent,
    ClientsListComponent
  ]
})
export class ClientsModule { }
