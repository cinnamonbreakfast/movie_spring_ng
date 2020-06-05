import { Component, Input, OnInit } from '@angular/core';
import { Client } from '../shared/client.model';
import { ClientService } from '../shared/client.service';
import { Router } from "@angular/router";

import { Page } from "../shared/criteria/page.model";
import { Filter } from "../shared/criteria/filter.model";
import { Sort } from "../shared/criteria/sort.model";
import { PageResult } from '../shared/page-result';

@Component({
  selector: 'app-client-list',
  templateUrl: './client-list.component.html',
  styleUrls: ['./client-list.component.scss']
})
export class ClientListComponent implements OnInit {
  errorMessage: string;
  clients: Array<Client>;
  selectedClient: Client;
  pages: number;
  currentPage: number = 0;
  elementsPerPage: number = 3;
  filterOn: boolean = false;

  filterAge: number = 20;

  filter: Filter;
  sort: Sort;

  @Input() filterProbe: Client;

  constructor(private clientService: ClientService,
              private router: Router) {
    this.filter = new Filter();
    this.sort = new Sort();
    this.filterProbe = this.filter.client;
  }

  ngOnInit(): void {
    this.getClients();
    this.setPages()
    this.currentPage = 0;
  }

  setPages()
  {
    this.clientService.getPages(0, this.elementsPerPage)
    .subscribe(
      pages => this.pages = pages,
      error => this.errorMessage = <any>error
    )
    this.currentPage = 0;
  }

  counter(i: number) {
    return new Array(i);
  }

  getClients() {
    // this.clientService.getPage(this.currentPage, this.elementsPerPage)
    //   .subscribe(
    //     clients => this.clients = clients,
    //     error => this.errorMessage = <any>error
    //   );

    let page = new Page();
    page.page = this.currentPage;
    page.size = this.elementsPerPage;

    let pageResult = new PageResult();

    this.clientService.getMapped(page, this.filter, this.sort)
      .subscribe(
        response => {
          pageResult = response,
          this.clients = response.clients;
          this.pages = response.pagesCount;
        },
        error => this.errorMessage = <any>error
      );
  }

  onSelect(client: Client): void {
    this.selectedClient = client;
    console.log(client.movieDTOList);
  }

  changePage(page: number): void {
    this.selectedClient = null;
    this.currentPage = page;
    this.getClients();
  }

  gotoDetail(): void {
    this.router.navigate(
      [ '/clients/detail', this.selectedClient.id ]
    )
  }

  toggleFilter(): void {
    if(this.filterOn == true)
    {
      this.filterOn = false;
    } else {
      this.filterOn = true;
    }
  }

  filterChangeAge(value: number): void {
    this.filterAge = value;
  }

  filterAction(e): void {
    e.preventDefault();

    this.currentPage = 0;

    this.getClients();
  }

  clearForm(): void {
    this.filter.clear();
  }

  filterField(event: any):void {
    // console.log(event.target.name, event.target.value);
    this.sort.and(event.target.name, event.target.value);
    console.log(this.sort.getMap());
  }
  
  deleteClient(): void {
    if(confirm("Are you sure you want to delete "+this.selectedClient.firstName+" "+this.selectedClient.secondName+"?"))
    {
      this.clientService.delete(this.selectedClient)
      .subscribe(
        _ => { this.changePage(0) },
      );
    }
  }

}
