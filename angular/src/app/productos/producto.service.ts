import { Injectable } from '@angular/core';
import { Producto } from './producto';
import { catchError, Observable, throwError } from 'rxjs';
import { HttpClient, HttpHeaderResponse, HttpHeaders } from '@angular/common/http';
import swal from 'sweetalert2';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class ProductoService {

  private urlEndPoint: string = 'http://localhost:8080/api/productos';

  private httpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' })

  public errorObject = null;

  constructor(private http: HttpClient, private router: Router) { }
  
  getProductos(): Observable<Producto[]> {
    return this.http.get<Producto[]>(this.urlEndPoint);
  }

  create(producto: Producto): Observable<Producto> {
    return this.http.post<Producto>(this.urlEndPoint, producto, { headers: this.httpHeaders }).pipe(
      catchError(err => {
        swal.fire('Los datos ingresados son erróneos', err.error.mensaje, 'error');
        this.errorObject = err;
        return throwError(err);
      }));
  }

  //Método auxiliar que retorna un producto es especifico
  getProducto(codigo: string): Observable<Producto>{
    return this.http.get<Producto>(`${this.urlEndPoint}/${codigo}`).pipe(
      catchError(err => {
        this.router.navigate(['/productos']);
        swal.fire('Error al obtener producto', err.error.mensaje, 'error');
        return throwError(() => err);
      })
    );
  }

  update(producto: Producto): Observable<Producto>{
    return this.http.put<Producto>(`${this.urlEndPoint}/${producto.codigo}`, producto, {headers: this.httpHeaders}).pipe(
      catchError(err => {
        swal.fire('Los datos ingresados son erróneos', err.error.mensaje, 'error');
        return throwError(() => err);
      })
    );
  }

  delete(codigo: string): Observable<Producto>{
    return this.http.delete<Producto>(`${this.urlEndPoint}/${codigo}`, {headers: this.httpHeaders}).pipe(
      catchError(err => {
        swal.fire('Error al eliminar producto', err.error.mensaje, 'error');
        return throwError(() => err);
      })
    );
  }
}
