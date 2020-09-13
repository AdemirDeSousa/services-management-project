import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { Client } from '../client';
import { ClientsService } from '../../clients.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-clients-form',
  templateUrl: './clients-form.component.html',
  styleUrls: ['./clients-form.component.css']
})
export class ClientsFormComponent implements OnInit {

  client: Client;
  success: boolean = false;
  errors: String[];
  id: number;

  constructor(
    private service: ClientsService,
    private router: Router,
    private activatedRoute: ActivatedRoute
    ) {
    this.client = new Client();
  }

  ngOnInit(): void {
    let params = this.activatedRoute.params;

    params.subscribe( urlParams => {
      this.id = urlParams['id'];

      if(this.id){
        this.service.getClientById(this.id).subscribe( response => {
        this.client = response;
      }, errorResponse => {
        this.client = new Client();
      });
      }
    });
  }

  backToList(){
    this.router.navigate(['/clients-list']);
  }

  onSubmit(){

    if(this.id){

      this.service.update(this.client).subscribe( response => {
        this.success = true;
        this.errors = null;
      }, errorResponse => {
        this.errors = ['Erro ao Atualizar o Cliente'];
      });

    } else {

      this.service.save(this.client).subscribe( response => {
        console.log(response);
        this.success = true;
        this.errors = null;
        this.client = response;
      }, errorResponse => {
        this.success = false;
        this.errors = errorResponse.error.errors;
      });

    }


  }
}
