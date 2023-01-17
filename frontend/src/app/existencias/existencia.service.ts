import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaderResponse, HttpHeaders } from '@angular/common/http';
import { Existencia } from './existencia';
import { AuthService } from '../usuarios/auth.service';

@Injectable({
  providedIn: 'root'
})
export class ExistenciaService {

  private urlEndPoint: string = 'http://localhost:8080/api/existencias';

  private httpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' })

constructor(private http: HttpClient ,private authService: AuthService) { }

  private agregarAuthorizationHeader(){
    let token = this.authService.token;
    if(token != null){
      return this.httpHeaders.append('Authorization', 'Bearer ' + token);
    }
    return this.httpHeaders;
  }

  public getExistencia(id: number): Observable<Existencia> {
    return this.http.get<Existencia>(`${this.urlEndPoint}/${id}`, {headers: this.agregarAuthorizationHeader()});
  }

  public getExistencias(): Observable<Existencia[]> {
    return this.http.get<Existencia[]>(this.urlEndPoint);
  }

  update(existencia: Existencia): Observable<Existencia>{
    return this.http.put<any>(`${this.urlEndPoint}/${existencia.id}`, existencia, { headers: this.agregarAuthorizationHeader() })
  }

  create(existencia: Existencia): Observable<Existencia> {
    return this.http.post<Existencia>(this.urlEndPoint, existencia, { headers: this.agregarAuthorizationHeader()});
  }

}

