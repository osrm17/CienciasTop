import { Component, OnInit } from '@angular/core';
import { Usuario } from './usuario';
import { UsuarioService } from './usuario.service';
import { Router, ActivatedRoute } from '@angular/router';
import swal from 'sweetalert2';

@Component({
  selector: 'app-form-resta-tarjeta',
  templateUrl: './form-resta-tarjeta.component.html',
  styleUrls: ['./form-resta-tarjeta.component.css']
})
export class FormRestaTarjetaComponent implements OnInit {
  titulo: string = "Restar Puntos"
  usuario: Usuario = new Usuario()
  puntos: number 
  
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

  public restar():void{
    this.usuario.pumaPuntos = this.puntos
    this.usuarioService.restaPuntos(this.usuario).subscribe(usuario => 
      {
      this.router.navigate(['/usuarios'])
      swal.fire('Cambios realizados.', `PumaPuntos utilizados.`, 'success')
      }
    )
  }
}
