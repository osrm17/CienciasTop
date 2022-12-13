import { Injectable } from '@angular/core';
import { Rentasdetalle } from './rentasdetalle';
import { Observable } from 'rxjs';
import { of } from 'rxjs';
import { HttpClient, HttpHeaderResponse, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class HistorialService {

  private urlEndPoint: string = 'http://localhost:8080/api/rentas';


  constructor(private http: HttpClient) { }

  getRentasDetalle(numct:string): Observable<Rentasdetalle[]> {
    return this.http.get<Rentasdetalle[]>(this.urlEndPoint+"/usuario/"+numct);
  }
}
