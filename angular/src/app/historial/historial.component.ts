import { Component, OnInit } from '@angular/core';
import { Rentasdetalle } from './rentasdetalle';
import { HistorialService } from './historial.service';

@Component({
  selector: 'app-historial',
  templateUrl: './historial.component.html',
  styleUrls: ['./historial.component.css']
})
export class HistorialComponent implements OnInit {

  historial: Rentasdetalle[];
  numct: string;

  constructor(private historialService: HistorialService) { }

  ngOnInit(): void {
    this.historial = []
  }

  getHistorial(): void {
    if (this.numct != "")
      this.historialService.getRentasDetalle(this.numct).subscribe(
        historial => this.historial = historial
      );
    else
      this.historial = []
  }

}
