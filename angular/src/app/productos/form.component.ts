import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Producto } from './producto';
import { ProductoService } from './producto.service';

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

  public create():void {
    this.productoService.create(this.producto).subscribe(
      response => this.router.navigate(['productos/'])
    )
  }

}
