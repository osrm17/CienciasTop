import { Component, LOCALE_ID, OnInit } from '@angular/core';
import { Producto } from '../productos/producto';
import { Usuario } from '../usuarios/usuario';
import { ReporteService } from './reporte.service';
import { formatDate, registerLocaleData } from '@angular/common';
import localeEs from '@angular/common/locales/es';

registerLocaleData(localeEs, 'es');


@Component({
  selector: 'app-reportes',
  templateUrl: './reportes.component.html',
  styleUrls: ['./reportes.component.css'],
  providers: [ { provide: LOCALE_ID, useValue: 'es' } ],
})
export class ReportesComponent implements OnInit {

  fecha = formatDate(new Date(), 'longDate', 'es');
  baratos: Producto[];
  masRentados: Producto[];
  devsUsuarios: Usuario[];
  masRentas: Usuario[];
  activos: String[];
  inactivos: Number;
  
  constructor(private reporteService: ReporteService) { }

  ngOnInit(): void {
    this.reporteService.devsUsuario().subscribe(
      devsUsuarios => this.devsUsuarios = devsUsuarios
    );

    this.reporteService.getActivos().subscribe(
      activos => this.activos = activos
    );

    this.reporteService.getInactivos().subscribe(
      inactivos => this.inactivos = inactivos
    );

    this.reporteService.masRentasUsuario().subscribe(
      masRentas => this.masRentas = masRentas
    );

    this.reporteService.getMasRentados().subscribe(
      masRentados => this.masRentados = masRentados
    );

    this.reporteService.getBaratos().subscribe(
      baratos => this.baratos = baratos
    );
  }



}
