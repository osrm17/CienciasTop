import { Component, OnInit } from '@angular/core';
import { Usuario } from './usuario';
import { UsuarioService } from './usuario.service';
import { Router, ActivatedRoute } from '@angular/router';
import swal from 'sweetalert2';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css']
})
export class FormComponent implements OnInit {

  titulo: string = "Añadir Usuario"
  usuario: Usuario = new Usuario()

  constructor(private usuarioService: UsuarioService, private router: Router, private activateRoute: ActivatedRoute) { }


  ngOnInit(): void {
  }


  public create():void{
    this.usuarioService.create(this.usuario).subscribe(usuario =>
      {
        this.router.navigate(['/usuarios'])
        swal.fire('Nuevo Usuario', `Usuario ${usuario.nombre} creado con éxito`, 'success')
      }
    )
  }

}



//--------------------------------------------------------------

/**
import { Component, OnInit } from '@angular/core';
import { Producto } from './producto';
import { ProductoService } from './producto.service';
import { Router, ActivatedRoute } from '@angular/router';
import swal from 'sweetalert2';
import { Observable } from 'rxjs';


@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css']
})
export class FormComponent implements OnInit {

  titulo: string = "Añadir Producto"
  producto: Producto = new Producto()

  constructor(private productoService: ProductoService, private router: Router, private activateRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.cargarProducto()
  }

  cargarProducto(): void{
    this.activateRoute.params.subscribe(params => {
      let id = params['id']
      if(id){
        this.productoService.getProducto(id).subscribe((producto)=>this.producto = producto)
      }
    })
  }

  public create():void{
    this.productoService.create(this.producto).subscribe(producto =>
      {
        this.router.navigate(['/productos'])
        swal.fire('Nuevo Producto', `Producto ${producto.nombre} creado con éxito`, 'success')
      }
    )
  }

  public update():void{
    this.productoService.update(this.producto).subscribe(producto => 
      {
      this.router.navigate(['/productos'])
      swal.fire('Producto Actualizado', `${producto.nombre} actualizado con éxito`, 'success')
      }
    )
  }



}

*/