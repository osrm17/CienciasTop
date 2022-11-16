import { Injectable } from '@angular/core';
import { Producto } from './producto';
import { catchError, Observable, throwError } from 'rxjs';
import { HttpClient, HttpHeaderResponse, HttpHeaders } from '@angular/common/http';
import swal from 'sweetalert2';

@Injectable({
  providedIn: 'root'
})
export class ProductoService {

  private urlEndPoint: string = 'http://localhost:8080/api/productos';

  private httpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' })

  public errorObject = null;

  constructor(private http: HttpClient) { }

  getProductos(): Observable<Producto[]> {
    return this.http.get<Producto[]>(this.urlEndPoint);
  }

  create(producto: Producto): Observable<Producto> {
    return this.http.post<Producto>(this.urlEndPoint, producto, { headers: this.httpHeaders }).pipe(
      catchError(err => {
        swal.fire('Ooops', `los datos ingresados son erroneos`, 'error');
        this.errorObject = err;
        return throwError(err);
      }));
  }
}
