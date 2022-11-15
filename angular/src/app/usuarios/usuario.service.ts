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

}
