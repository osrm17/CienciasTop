import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute} from '@angular/router';
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
export class FormComponentProducto implements OnInit {

  titulo: string = "Producto"
  producto: Productostock = new Productostock()
  productos: Producto = new Producto()

  constructor(private existenciaService: ExistenciaService, private productoService: ProductoService, private router: Router, private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.cargarProducto()
  }

  cargarProducto(): void{
    this.activatedRoute.params.subscribe(params => {
      let codigo = params['codigo']
      if(codigo){
        this.productoService.getProducto(codigo).subscribe((productos)=> this.productos = productos)
      }
    })
  }

  public update():void{
    this.productoService.update(this.productos).subscribe(productos => 
      {
      this.router.navigate(['/productos'])
      swal.fire('Producto actualizado', `El producto ${this.productos.nombre} ha sido actualizado con éxito`, 'success')
      }
    )
  }

  public create(): void {
    this.productoService.create(this.producto).subscribe(producto => {
      this.router.navigate(['productos/'])
      swal.fire('Nuevo Producto', `El producto ${this.producto.nombre} se ha agregado con éxito`, 'success')

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
