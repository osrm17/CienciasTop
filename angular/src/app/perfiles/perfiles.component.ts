import { Component, OnInit } from '@angular/core';
import { Usuario } from './usuario';
import { PerfilService } from './perfil.service';
import { AuthService } from '../usuarios/auth.service';

@Component({
  selector: 'app-perfiles',
  templateUrl: './perfiles.component.html',
  styleUrls: ['./perfiles.component.css']
})
export class PerfilesComponent implements OnInit {
  perfil: Usuario;
  
  constructor(private perfilService: PerfilService, private authService: AuthService) {
    this.perfil = new Usuario();
  }

  ngOnInit(): void {

    //  this.perfilService.getPerfil().subscribe(
    //     perfiles => this.perfil = perfiles
    //  );
    this.perfil = this.authService.usuario;
  }

}
