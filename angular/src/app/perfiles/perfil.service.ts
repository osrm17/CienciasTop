import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Usuario } from './usuario';
import swal from 'sweetalert2';

@Injectable({
  providedIn: 'root'
})

export class PerfilService {
  private urlEndPoint: string = 'http://localhost:8080/api/usuarios/331851247';
  
  constructor(private http: HttpClient) { }
  
  public getPerfil(): Observable<Usuario> {
    return this.http.get<Usuario>(this.urlEndPoint);
  }
}
