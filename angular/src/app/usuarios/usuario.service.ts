import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { of } from 'rxjs';
import { Usuario } from './usuario';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, throwError } from 'rxjs';
import swal from 'sweetalert2';
import { Router } from '@angular/router';


@Injectable({
  providedIn: 'root'
})

export class UsuarioService {

  private urlEndPoint: string = 'http://localhost:8080/api/usuarios'
  private urlEndPointSuma: string = 'http://localhost:8080/api/usuarios/suma'
  private urlEndPointResta: string = 'http://localhost:8080/api/usuarios/resta'
  private httpHeaders = new HttpHeaders({'Content-Type': 'application/json'})

  public errorObject = null;


  constructor(private http: HttpClient, private router: Router) { }

  getUsuarios(): Observable<Usuario[]> {
    return this.http.get<Usuario[]>(this.urlEndPoint);
  }

  create(usuario: Usuario): Observable<Usuario> {
    return this.http.post<Usuario>(this.urlEndPoint, usuario, { headers: this.httpHeaders }).pipe(
      catchError(err => {
        swal.fire('Los datos ingresados son erróneos', err.error.mensaje, 'error');
        this.errorObject = err;
        return throwError(err);
      }));
  }

  //Método auxiliar que retorna un usuario es especifico
  getUsuario(numct: string): Observable<Usuario>{
    return this.http.get<Usuario>(`${this.urlEndPoint}/${numct}`).pipe(
      catchError(err => {
        this.router.navigate(['/usuarios']);
        swal.fire('Error al obtener usuario', err.error.mensaje, 'error');
        return throwError(() => err);
      })
    );
  }

  update(usuario: Usuario): Observable<Usuario>{
    return this.http.put<Usuario>(`${this.urlEndPoint}/${usuario.numct}`, usuario, {headers: this.httpHeaders}).pipe(
      catchError(err => {
        swal.fire('Los datos ingresados son erróneos', err.error.mensaje, 'error');
        this.errorObject = err;
        return throwError(err);
      }));
  }

  delete(numct: string): Observable<Usuario>{
    return this.http.delete<Usuario>(`${this.urlEndPoint}/${numct}`, {headers: this.httpHeaders}).pipe(
      catchError(err => {
        swal.fire('Error al eliminar usuario', err.error.mensaje, 'error');
        return throwError(() => err);
      })
    );
  }

  sumaPuntos(usuario: Usuario): Observable<Usuario>{
    return this.http.put<Usuario>(`${this.urlEndPoint}/sumar/${usuario.numct}`, usuario, {headers: this.httpHeaders}).pipe(
      catchError(err => {
        //this.router.navigate(['/usuarios'])
        swal.fire('Error', "No se pudieron añadir los PumaPuntos", 'error');
        this.errorObject = err;
        return throwError(err);
      }));
  }


  restaPuntos(usuario: Usuario): Observable<Usuario>{
    return this.http.put<Usuario>(`${this.urlEndPoint}/restar/${usuario.numct}`, usuario, {headers: this.httpHeaders}).pipe(
      catchError(err => {
        //this.router.navigate(['/usuarios'])
        swal.fire('Error', "No se pudieron restar los PumaPuntos", 'error');
        this.errorObject = err;
        return throwError(err);
      }));
  }

}

