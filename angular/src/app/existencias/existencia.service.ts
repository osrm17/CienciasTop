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

  public getExistencias(): Observable<Existencia[]> {
    return this.http.get<Existencia[]>(this.urlEndPoint);
  }

  create(existencia: Existencia): Observable<Existencia> {
    return this.http.post<Existencia>(this.urlEndPoint, existencia, { headers: this.agregarAuthorizationHeader()});
  }

}

