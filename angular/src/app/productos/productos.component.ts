import { Component, OnInit } from '@angular/core';
import Swal from 'sweetalert2';
import { Producto } from './producto';
import { ProductoService } from './producto.service';
import { AuthService } from '../usuarios/auth.service';

@Component({
  selector: 'app-productos',
  templateUrl: './productos.component.html',
  styleUrls: ['./productos.component.css']
})
export class ProductosComponent implements OnInit {

  productos: Producto[];
  productoPrueba: Producto = new Producto

  constructor(private productoService: ProductoService, public authService: AuthService ) { }

  ngOnInit(): void {
    this.productoService.getProductos().subscribe(
      productos => this.productos = productos
    );
  }

  delete(producto: Producto): void {
    const swalWithBootstrapButtons = Swal.mixin({
      customClass: {
        confirmButton: 'btn btn-success',
        cancelButton: 'btn btn-danger'
      },
      buttonsStyling: false
    })
    swalWithBootstrapButtons.fire({
      title: '¿Está seguro?',
      text: `¿Está seguro que desea eliminar ${producto.nombre}?`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Confirmar',
      cancelButtonText: 'Cancelar',
      reverseButtons: true
    }).then((result) => {
      if (result.isConfirmed) {
        this.productoService.delete(producto.codigo).subscribe(
          Response => {
            this.productos =  this.productos.filter(prod => prod !== producto)
            swalWithBootstrapButtons.fire(
              '¡Producto eliminado!',
              'El producto se ha eliminado con éxito.',
              'success'
            )
          }
        )
      }
    })
  }

  public leeProducto():void{
  }

}
