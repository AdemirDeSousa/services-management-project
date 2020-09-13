import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { ClientsService } from 'src/app/clients.service';
import { Client } from '../client';

@Component({
  selector: 'app-clients-list',
  templateUrl: './clients-list.component.html',
  styleUrls: ['./clients-list.component.css']
})
export class ClientsListComponent implements OnInit {

  clients: Client[] = [];
  selectedClient: Client;
  successMsg: String;
  errorMsg: String;

  constructor(
    private service: ClientsService,
    private router: Router
    ) {}

  ngOnInit(): void {
    this.service.getClients()
      .subscribe( response => this.clients = response );
  }

  newRegister(){
    this.router.navigate(['/clients-form']);
  }

  beforeDelete(client: Client){
    this.selectedClient = client;
  }

  deleteClient(){
    this.service.delete(this.selectedClient).subscribe( response => {
      this.successMsg = 'Cliente deletado com Sucesso!';
      this.ngOnInit();
    }, errorResponse => {
      this.errorMsg = 'Ocorreu um erro ao deletar o Cliente.';
    });
  }
}
