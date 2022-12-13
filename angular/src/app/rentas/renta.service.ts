import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaderResponse, HttpHeaders } from '@angular/common/http';
import { Renta } from './renta';

@Injectable({
  providedIn: 'root'
})
export class RentaService {

  private urlEndPoint: string = 'http://localhost:8080/api/rentas';

  private httpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' })

  constructor(private http: HttpClient) { }

  public getRentas(): Observable<Renta[]> {
    return this.http.get<Renta[]>(this.urlEndPoint);
  }

  create(renta: Renta): Observable<Renta> {
    return this.http.post<Renta>(this.urlEndPoint, renta, { headers: this.httpHeaders });
  }

}