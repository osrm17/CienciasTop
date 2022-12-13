import { Component, OnInit } from '@angular/core';
import { Producto } from './producto';
import { ProductoService } from './producto.service';
import { Router, ActivatedRoute } from '@angular/router';
import swal from 'sweetalert2';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-formbuscarproductos',
  templateUrl: './formbuscarproductos.component.html',
  styleUrls: ['./formbuscarproductos.component.css']
})
export class FormbuscarproductosComponent implements OnInit {

  titulo: string = "BUSCAR PRODUCTO"
  producto: Producto = new Producto()

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
}
