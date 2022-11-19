import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute} from '@angular/router';
import { Producto } from './producto';
import { ProductoService } from './producto.service';
import swal from 'sweetalert2';
import { Productostock } from './productostock';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-formeditar',
  templateUrl: './formeditar.component.html',
  styleUrls: ['./form.editarcomponent.css']
})
export class FormComponentProducto implements OnInit {

titulo: string = "EDITAR PRODUCTO"
  producto: Productostock = new Productostock()

  constructor(private productoService: ProductoService, private router: Router) { }

  ngOnInit(): void {
    this.cargarProducto();
  }

  cargarProducto() : void{
    this.productoService.getProductos().subscribe(producto => {
      this.router.navigate(['productos/formeditar'])
    }
  }

  public update():void{
    this.productoService.update(this.producto).subscribe(producto => 
      {
      this.router.navigate(['/productos/formeditar'])
      swal.fire('Producto actualizado', `El producto ${this.producto.nombre} ha sido actualizado con éxito`, 'success')
      }
    )
  }
}