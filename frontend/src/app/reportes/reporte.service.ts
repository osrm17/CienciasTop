import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Producto } from '../productos/producto';
import { of } from 'rxjs';
import { HttpClient, HttpHeaderResponse, HttpHeaders } from '@angular/common/http';
import { Usuario } from '../usuarios/usuario';

@Injectable({
  providedIn: 'root'
})
export class ReporteService {

  private urlEndPoint: string = 'http://localhost:8080/api/reportes';

  constructor(private http: HttpClient) { }

  getActivos(): Observable<String[]> {
    return this.http.get<String[]>(`${this.urlEndPoint}/activos`);
  }

  getInactivos(): Observable<Number> {
    return this.http.get<Number>(`${this.urlEndPoint}/inactivos`);
  }

  getBaratos(): Observable<Producto[]> {
    return this.http.get<Producto[]>(`${this.urlEndPoint}/baratos`);
  }

  getMasRentados(): Observable<Producto[]> {
    return this.http.get<Producto[]>(`${this.urlEndPoint}/masrentados`);
  }

  devsUsuario(): Observable<Usuario[]> {
    return this.http.get<Usuario[]>(`${this.urlEndPoint}/devtardias`);
  }

  masRentasUsuario(): Observable<Usuario[]> {
    return this.http.get<Usuario[]>(`${this.urlEndPoint}/masrentas`);
  }

}
