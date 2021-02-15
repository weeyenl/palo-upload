import { Injectable } from '@angular/core';
import { HttpClient, HttpEvent, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root'
})

export class DataService {
    constructor(private http: HttpClient) { }

    getData(sort: string, order: string, page: number, size: number): Observable<any> {
        const href = 'http://localhost:8080/orders';
        const requestUrl =
            `${href}?sort=${sort}&order=${order}&page=${page + 1}&size=${size}`;
    
        return this.http.get<any>(requestUrl);
      }
}