import { Component, OnInit } from '@angular/core';
import { Usuario } from './usuario';
import { UsuarioService } from './usuario.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-formbuscar',
  templateUrl: './formbuscar.component.html',
  styleUrls: ['./formbuscar.component.css']
})
export class FormbuscarComponent implements OnInit {

  titulo: string = "BUSCAR USUARIO"
  usuario: Usuario = new Usuario()
  usuarios: Usuario[];

  constructor(private usuarioService: UsuarioService, private router: Router, private activateRoute: ActivatedRoute) {
    this.usuarios = [];
  }

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

  delete(usuario: Usuario): void {
    const swalWithBootstrapButtons = Swal.mixin({
      customClass: {
        confirmButton: 'btn btn-success',
        cancelButton: 'btn btn-danger'
      },
      buttonsStyling: false
    })
    swalWithBootstrapButtons.fire({
      title: '¿Está seguro?',
      text: `¿Está seguro que desea elimiar al usuario ${usuario.nombre}?`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Confirmar',
      cancelButtonText: 'Cancelar',
      reverseButtons: true
    }).then((result) => {
      if (result.isConfirmed) {
        this.usuarioService.delete(usuario.numct).subscribe(
          Response => {
            this.usuarios =  this.usuarios.filter(usua => usua !== usuario)
            swalWithBootstrapButtons.fire(
              '¡Usuario eliminado!',
              'El usuario se ha eliminado con éxito.',
              'success'
            )
          }
        )
      }
    })
  }
}
