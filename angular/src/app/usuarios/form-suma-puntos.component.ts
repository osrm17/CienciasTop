import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { UsuarioService } from './usuario.service';
import swal from 'sweetalert2';
import { Usuario } from './usuario';

@Component({
  selector: 'app-form-suma-puntos',
  templateUrl: './form-suma-puntos.component.html',
  styleUrls: ['./form-suma-puntos.component.css']
})
export class FormSumaPuntosComponent implements OnInit {
  titulo: string = "Sumar Puntos"
  usuario: Usuario = new Usuario()
  constructor(private usuarioService: UsuarioService, private router: Router, private activateRoute: ActivatedRoute) { 
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

  public sumar():void{
    this.usuarioService.sumaPuntos(this.usuario).subscribe(usuario => 
      {
      this.router.navigate(['/usuarios'])
      swal.fire('Cambios realizados', `PumaPuntos acumulados.`, 'success')
      }
    )
  }

  
  }
