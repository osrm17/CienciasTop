import { Component, OnInit } from '@angular/core';
import { Renta } from './renta';
import { RentaService } from './renta.service';

@Component({
    selector: 'app-rentas',
    templateUrl: './rentas.component.html',
    styleUrls: ['./rentas.component.css']
  })
  export class RentasComponent implements OnInit {
  
    rentas: Renta[];
  
    constructor(private rentaService: RentaService) {
      this.rentas = []
    }
  
    ngOnInit(): void {
      this.rentaService.getRentas().subscribe(
        rentas => this.rentas = rentas
      );
    }
  
  }