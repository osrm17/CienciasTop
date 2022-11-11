import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Producto } from './producto';
import { ProductoService } from './producto.service';
import swal from 'sweetalert2';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css']
})
export class FormComponent implements OnInit {

  titulo: string = "Agregar producto"
  producto: Producto = new Producto()

  constructor(private productoService: ProductoService, private router: Router) { }

  ngOnInit(): void {
  }

  public create(): void {
    this.productoService.create(this.producto).subscribe(producto =>
      {
         this.router.navigate(['productos/'])
         swal.fire('Nuevo Producto', `Producto ${producto.nombre} creado con éxito`, 'success')
      }
    )
  }

}
