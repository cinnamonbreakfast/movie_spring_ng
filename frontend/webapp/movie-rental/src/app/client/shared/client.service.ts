import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpResponse, HttpHeaders } from '@angular/common/http';
import { Client } from './client.model';

import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { throws } from 'assert';

import { Filter } from './criteria/filter.model';
import { Page } from './criteria/page.model';
import { Sort } from './criteria/sort.model';
import { PageResult } from './page-result';

@Injectable()
export class ClientService {
  private clientsURL = 'http://localhost:8080/clients';

  constructor(private httpClient: HttpClient) { }

  getClients(): Observable<Client[]> {
    return this.httpClient
      .get<Array<Client>>(this.clientsURL);
  }

  getClient(id: number): Observable<Client> {
    return this.getClients()
      .pipe(
        map(clients => clients.find(
            client => client.id === id
          )
        )
      );
  }

  update(client): Observable<Client> {
    const url = `${this.clientsURL}/${client.id}`;

    return this.httpClient
      .put<Client>(url, client);
  }

  save(client: Client): Observable<Client> {
    return this.httpClient.post<Client>(this.clientsURL, client);
  }

  // OBSOLETTE
  getPages(page: number, count: number): Observable<number> {
    const url = `${this.clientsURL}/page/total/${page}/${count}`;

    return this.httpClient.get<number>(url);
  }

  // OBSOLETTE
  getPage(page: number, count: number): Observable<Array<Client>> {
    let pageRequest = new Page();
    pageRequest.page = page;
    pageRequest.size = count;
    const url = `${this.clientsURL}/page`;

    return this.httpClient.post<Array<Client>>(url, pageRequest);
  }

  getMapped(page: Page, filter: Filter, sort: Sort): Observable<PageResult> {
    let params = new HttpParams();
    
    const url = `${this.clientsURL}/page/${page.page}/${page.size}?sorting=`;

    return this.httpClient.post<PageResult>(
      url,
      filter.getProbe()
    );
  }

  delete(client: Client): Observable<any> {
    const url = `${this.clientsURL}/${client.id}`;

    return this.httpClient.delete(
      url
    )
  }
}
