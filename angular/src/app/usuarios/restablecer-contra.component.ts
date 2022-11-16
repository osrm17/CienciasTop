import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Restablecer } from './restablecer';
import { Usuario } from './usuario';
import { UsuarioService } from './usuario.service';
import swal from 'sweetalert2';


@Component({
  selector: 'app-restablecer-contra',
  templateUrl: './restablecer-contra.component.html',
  styleUrls: ['./restablecer-contra.component.css']
})
export class RestablecerContraComponent implements OnInit {

  titulo: string = "RESTABLECER CONTRASENIA";
  restablecer: Restablecer = new Restablecer();
  usuario: Usuario;

  constructor(private usuarioService: UsuarioService, private router: Router, private activateRoute: ActivatedRoute) { }

  ngOnInit(): void {
  }

  public restablece(): void {

    console.log(this.restablecer);

    if (this.restablecer.nuevaContra != this.restablecer.confirmacionContra) {
      swal.fire('Contrasenia no actualizada', `Las contrasenias no coindicen`, 'error');
    }

    this.usuarioService.getUsuario(this.restablecer.numct).subscribe((usuario) => {
      if (usuario == null)
        swal.fire('Contrasenia no actualizada', `Usuario no existe en el sistema o las contrasenia no es valida`, 'error');
      this.usuario = usuario;

      usuario.contrasenia = this.restablecer.nuevaContra;
      
      this.usuarioService.update(this.usuario).subscribe(() => {
        this.router.navigate(['/usuarios'])
        swal.fire('Contrasenia restablecida', `El usuario ${this.usuario.nombre} ha sido restablecido contrasenia`, 'success')
      }
      )
    });

  }

  private update(): void {
    this.usuarioService.update(this.usuario).subscribe(() => {
      this.router.navigate(['/usuarios'])
      swal.fire('Contrasenia restablecida', `El usuario ${this.usuario.nombre} ha sido restablecido contrasenia`, 'success')
    }
    )
  }


}
