import { Component, OnInit } from '@angular/core';
import { Existencia } from './existencia';
import { ExistenciaService } from './existencia.service';

@Component({
  selector: 'app-existencias',
  templateUrl: './existencias.component.html',
  styleUrls: ['./existencias.component.css']
})
export class ExistenciasComponent implements OnInit {

  existencias: Existencia[];

  constructor(private existenciaService: ExistenciaService) {
    this.existencias = []
  }

  ngOnInit(): void {
    this.existenciaService.getExistencias().subscribe(
      existencias => this.existencias = existencias
    );
  }

}
