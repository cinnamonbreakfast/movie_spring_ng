import { Client } from './client.model';

export class PageResult {
    clients: Array<Client>;
    page: number;
    pagesCount: number;
}
