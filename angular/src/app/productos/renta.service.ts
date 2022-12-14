import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaderResponse, HttpHeaders } from '@angular/common/http';
import { Renta } from './renta';
import { Existencia } from '../existencias/existencia';
import { Producto } from './producto';


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

  getExistencias(): Observable<Existencia[]> {
    return this.http.get<Existencia[]>(`${this.urlEndPoint}/existencias`);
  }

  getProductos(): Observable<Producto[]> {
    return this.http.get<Producto[]>(`${this.urlEndPoint}/productos`);
  }

  create(renta: Renta): Observable<Renta> {
    return this.http.post<Renta>(this.urlEndPoint, renta, { headers: this.httpHeaders });
  }

}