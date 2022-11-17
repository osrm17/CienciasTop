import { Component, OnInit } from '@angular/core';
import { Usuario } from './usuario';
import { UsuarioService } from './usuario.service';
import { Router, ActivatedRoute } from '@angular/router';
import swal from 'sweetalert2';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-formbuscar',
  templateUrl: './formbuscar.component.html',
  styleUrls: ['./formbuscar.component.css']
})
export class FormbuscarComponent implements OnInit {

  titulo: string = "BUSCAR USUARIO"
  usuario: Usuario = new Usuario()

  constructor(private usuarioService: UsuarioService, private router: Router, private activateRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.cargarUsuario()
  }

  cargarUsuario(): void{
    this.activateRoute.params.subscribe(params => {
      let numct = params['numct']
      if(numct){
        this.usuarioService.getUsuario(numct).subscribe((usuario)=>this.usuario = usuario)
      }
    })
  }

  public create():void{
    this.usuarioService.create(this.usuario).subscribe(usuario =>
      {
        this.router.navigate(['/usuarios'])
        swal.fire('Nuevo Usuario', `El usuario ${this.usuario.nombre} se ha agregado con éxito`, 'success')
      }
    )
  }
}
