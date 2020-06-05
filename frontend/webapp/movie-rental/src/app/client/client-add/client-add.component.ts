import { Component, OnInit, Input } from '@angular/core';
import { ClientService } from '../shared/client.service';
import {Location} from '@angular/common';

@Component({
  selector: 'app-client-add',
  templateUrl: './client-add.component.html',
  styleUrls: ['./client-add.component.scss']
})
export class ClientAddComponent implements OnInit {

  constructor(private clientService: ClientService,
    private location: Location
  ) {
  }

  ngOnInit(): void {
    
  }

  goBack(): void {
    this.location.back();
  }

  save(firstName: string, secondName: string, age: number, job: string): void {
    this.clientService.save({
      id: 0,
      firstName,
      secondName,
      job,
      age: +age,
      movieDTOList: []
    }).subscribe(client => {alert(client.firstName + " " + client.secondName + " added!"); this.goBack(); });
  }

}
