import { Injectable } from '@angular/core';
import { Rentasdetalle } from './rentasdetalle';
import { catchError, Observable, throwError } from 'rxjs';
import { of } from 'rxjs';
import { HttpClient, HttpHeaderResponse, HttpHeaders } from '@angular/common/http';
import Swal from 'sweetalert2';

@Injectable({
  providedIn: 'root'
})
export class HistorialService {

  private urlEndPoint: string = 'http://localhost:8080/api/rentas';


  constructor(private http: HttpClient) { }

  getRentasDetalle(numct:string): Observable<Rentasdetalle[]> {
    return this.http.get<Rentasdetalle[]>(this.urlEndPoint+"/usuario/"+numct).pipe(
      catchError(e => {
        Swal.fire('Error al buscar historial', e.error.mensaje, 'error');
        return throwError( () => e );
      })
    )
  }
}
