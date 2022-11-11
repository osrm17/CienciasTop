import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Producto } from './producto';
import { ProductoService } from './producto.service';
import swal from 'sweetalert2';
import { Productostock } from './productostock';
import { Existencia } from '../existencias/existencia';
import { ExistenciaService } from '../existencias/existencia.service';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css']
})
export class FormComponent implements OnInit {

  titulo: string = "Agregar producto"
  producto: Productostock = new Productostock()

  constructor(private existenciaService: ExistenciaService, private productoService: ProductoService, private router: Router) { }

  ngOnInit(): void {
  }

  public create(): void {
    this.productoService.create(this.producto).subscribe(producto => {
      this.router.navigate(['productos/'])
      swal.fire('Nuevo Producto', `Producto ${producto.nombre} creado con éxito`, 'success')

      for (let index = 0; index < this.producto.existencias; index++) {
        var existencia = new Existencia();
        existencia.codigo = this.producto.codigo;
        existencia.estaRentado = false;
        this.existenciaService.create(existencia).subscribe();
      }
    }
    )
  }
}
