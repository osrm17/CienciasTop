import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { of } from 'rxjs';
import { Usuario } from './usuario';
import { HttpClient, HttpHeaders } from '@angular/common/http';
//import { USUARIOS } from './usuarios.json';

@Injectable({
  providedIn: 'root'
})

export class UsuarioService {

  private urlEndPoint: string = 'http://localhost:8080/api/usuarios'

  private httpHeaders = new HttpHeaders({'Content-Type': 'application/json'})

  constructor(private http: HttpClient) { }

  getUsuarios(): Observable<Usuario[]> {
    return this.http.get<Usuario[]>(this.urlEndPoint);
  }

  create(usuario: Usuario): Observable<Usuario>{
    return this.http.post<Usuario>(this.urlEndPoint, usuario, {headers: this.httpHeaders} )
  }
}
