import { Component, OnInit } from '@angular/core';
import { Renta } from './renta';
import { formatDate, registerLocaleData } from '@angular/common';
import { RentaService } from './renta.service';
import { Existencia } from '../existencias/existencia';
import { Producto } from './producto';

@Component({
    selector: 'app-rentas',
    templateUrl: './rentas.component.html',
    styleUrls: ['./rentas.component.css']
  })
  export class RentasComponent implements OnInit {
  
    fecha = formatDate(new Date(), 'longDate', 'es');
    rentas: Renta[];
    existencias: Existencia[];
    productos: Producto[];
  
    constructor(private rentaservice: RentaService) {
      this.rentas = []
    }
  
    ngOnInit(): void {
      this.rentaservice.getRentas().subscribe(
        rentas => this.rentas = rentas
      );

      this.rentaservice.getExistencias().subscribe(
        existencias => this.existencias = existencias
      );

      this.rentaservice.getProductos().subscribe(
        productos => this.productos = productos
      );
    }
  
  }