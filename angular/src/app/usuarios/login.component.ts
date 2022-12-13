import { Component, OnInit } from '@angular/core';
import { Usuario } from './usuario';
import Swal from 'sweetalert2';
import { AuthService } from './auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  titulo:string = 'Ingresa tu número de cuenta y contraseña.';
  usuario: Usuario;

  constructor(private authService: AuthService, private router: Router) { 
    this.usuario = new Usuario();
  }

  ngOnInit(): void {
    if(this.authService.isAuthenticated()){
      Swal.fire('Login', `Hola ${this.authService.usuario.numct} ya has iniciado sesión.`, 'info');
      this.router.navigate(['/productos']);
    }
  }

  login(){
      console.log(this.usuario);
      if(this.usuario.numct == (null || "") || this.usuario.contrasenia == (null || "")){
        Swal.fire('Error Login', 'No se ingresó el número de cuenta o la contraseña.', 'error');
        return;
      }
      
      this.authService.login(this.usuario).subscribe(response => {
        console.log(response);

        this.authService.guardarUsuario(response.access_token);

        this.authService.guardarToken(response.access_token);

        let usuario = this.authService.usuario;

        this.router.navigate(['/productos']);
        Swal.fire('Inicio de sesión exitoso', `¡Bienvenido, ${usuario.nombre} con numct: ${usuario.numct}!`, 'success');
      },
      err => {
        if(err.status == 400){
          Swal.fire('Error al momento de iniciar sesión', 'El usuario o la contraseña ingresadas son incorrectos.', 'error');
        }
      }
      );
  }

}
