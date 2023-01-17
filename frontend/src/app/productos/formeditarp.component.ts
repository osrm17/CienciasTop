import { Component, OnInit } from '@angular/core';
import { Producto } from './producto';
import { ProductoService } from './producto.service';
import { Router, ActivatedRoute } from '@angular/router';
import swal from 'sweetalert2';

@Component({
  selector: 'app-formeditarp',
  templateUrl: './formeditarp.component.html',
  styleUrls: ['./formeditarp.component.css']
})
export class FormeditarPComponent implements OnInit {

  titulo: string = "EDITAR PRODUCTO"
  producto: Producto = new Producto()

  constructor(private productoService: ProductoService, private router: Router, private activateRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.cargarUsuario()
  }

  cargarUsuario(): void{
    this.activateRoute.params.subscribe(params => {
      let codigo = params['codigo']
      if(codigo){
        this.productoService.getProducto(codigo).subscribe((producto)=>this.producto = producto)
      }
    })
  }

  
  public update():void{
    this.productoService.update(this.producto).subscribe(producto =>
      {
      this.router.navigate(['/productos'])
      swal.fire('Producto actualizado', `El producto ${this.producto.nombre} ha sido actualizado con éxito`, 'success')
      }
    )
  }
}