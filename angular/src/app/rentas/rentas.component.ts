import { Component, LOCALE_ID, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Existencia } from '../existencias/existencia';
import { ExistenciaService } from '../existencias/existencia.service';
import { AuthService } from '../usuarios/auth.service';
import { Usuario } from '../usuarios/usuario';
import { Renta } from './renta';
import { RentaService } from './renta.service';
import { formatDate, registerLocaleData } from '@angular/common';
import localeEs from '@angular/common/locales/es';
import swal from 'sweetalert2';

registerLocaleData(localeEs, 'es');

@Component({
  selector: 'app-rentas',
  templateUrl: './rentas.component.html',
  styleUrls: ['./rentas.component.css'],
  providers: [{ provide: LOCALE_ID, useValue: 'es' }]
})
export class RentasComponent implements OnInit {

  //rentas: Renta[];
  renta: Renta = new Renta();
  usuario: Usuario = new Usuario();
  existencia: Existencia = new Existencia();
  fecha = formatDate(new Date(), 'yyyy-MM-dd', 'es');

  constructor(private rentaService: RentaService,
    private existenciaService: ExistenciaService,
    private authService: AuthService,
    private router: Router,
    private activateRoute: ActivatedRoute) {
  }

  ngOnInit(): void {

    this.usuario = this.authService.usuario;

    //this.rentaService.getRentas().subscribe(
    // rentas => this.rentas = rentas
    //);

    this.rentar();
  }

  rentar(): void {
    this.activateRoute.params.subscribe(params => {
      let id = params['id']
      if (id) {

        // obtener la existencia que tiene el numero de id 
        this.existenciaService.getExistencia(id).subscribe((existencia) => {

          this.existencia = existencia

          // crear mi registro renta
          this.renta.idExistencia = id;
          this.renta.fechaRenta = this.fecha;
          this.renta.numct = this.usuario.numct;
          this.renta.fechaDevolucion = null;

          // me subscribo a la creacion del registro renta
          this.rentaService.create(this.renta).subscribe(() => {
            this.existencia.estaRentado = true;
            this.existenciaService.update(existencia).subscribe(() => {
              this.router.navigate(['../historial']);
              swal.fire('Producto rentado', `${this.usuario.nombre} rentaste ${this.existencia.codigo}`, 'success');
            });
          });

        });
      }
    });
  }

}


  //this.rentaService.create(this.renta).subscribe(renta => this.renta = renta)
          //this.rentaService.create(this.renta).subscribe()

            // this.existencia.estaRentado = true;

            // this.existenciaService.update(this.existencia).subscribe()

            // this.router.navigate(['../historial']);
            // swal.fire('Producto rentado', `${this.usuario.nombre} rentaste ${this.existencia.codigo}`, 'success');
