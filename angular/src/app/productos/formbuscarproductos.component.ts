import { Component, OnInit } from '@angular/core';
import { Producto } from './producto';
import { ProductoService } from './producto.service';
import { Router, ActivatedRoute } from '@angular/router';
import Swal from 'sweetalert2';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-formbuscarproductos',
  templateUrl: './formbuscarproductos.component.html',
  styleUrls: ['./formbuscarproductos.component.css']
})
export class FormbuscarproductosComponent implements OnInit {

  titulo: string = "BUSCAR PRODUCTO"
  producto: Producto = new Producto()
  productos: Producto[];

  constructor(private productoService: ProductoService, private router: Router, private activateRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.cargarProducto()
  }

  cargarProducto(): void{
    this.activateRoute.params.subscribe(params => {
      let codigo = params['codigo']
      if(codigo){
        this.productoService.getProducto(codigo).subscribe((producto)=>this.producto = producto)
      }
    })
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
}
