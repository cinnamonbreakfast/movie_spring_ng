import { Client } from '../client.model';

export class Filter {
    public client: Client;

    constructor() {
        this.client = new Client();
    }

    getProbe(): Client {
        if(this.client.firstName == "") this.client.firstName = null;
        if(this.client.secondName == "") this.client.secondName = null;
        if(this.client.job == "") this.client.job = null;
        if(this.client.age == 0) this.client.age = null;

        return this.client;
    }

    clear(): void {
        this.client.firstName = null;
        this.client.secondName = null;
        this.client.job = null;
        this.client.age = null;
    }
}
