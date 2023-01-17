import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { HistorialComponent } from './historial/historial.component';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { ProductosComponent } from './productos/productos.component';
import { RouterModule, Routes } from '@angular/router';
import { UsuariosComponent } from './usuarios/usuarios.component';
import { HttpClientModule } from '@angular/common/http';
import { FormComponentProducto } from './productos/form.component';
import { FormComponentUsuario } from './usuarios/form.component';
import { FormsModule } from '@angular/forms';
import { ExistenciasComponent } from './existencias/existencias.component';
import { FormeditarComponent } from './usuarios/formeditar.component';
import { FormSumaPuntosComponent } from './usuarios/form-suma-puntos.component';
import { FormRestaPuntosComponent } from './usuarios/form-resta-puntos.component';
import { FormbuscarComponent } from './usuarios/formbuscar.component';
import { FormbuscarproductosComponent } from './productos/formbuscarproductos.component';
import { RestablecerContraComponent } from './usuarios/restablecer-contra.component';
import { PerfilesComponent } from './perfiles/perfiles.component';
import { LoginComponent } from './usuarios/login.component';
import { FormSumaTarjetaComponent } from './usuarios/form-suma-tarjeta.component';
import { FormRestaTarjetaComponent } from './usuarios/form-resta-tarjeta.component';
import { FormeditarPComponent } from './productos/formeditarp.component';

import { ReportesComponent } from './reportes/reportes.component';
import { RentasComponent } from './rentas/rentas.component';

const routes: Routes = [
  { path: '', redirectTo: '', pathMatch: 'full' },
  { path: 'historial', component: HistorialComponent},
  { path: 'usuarios', component: UsuariosComponent },
  { path: 'productos', component: ProductosComponent},
  { path: 'existencias', component: ExistenciasComponent},
  { path: 'productos/form', component: FormComponentProducto},
  { path: 'usuarios/form', component: FormComponentUsuario},
  { path: 'usuarios/formeditar/:numct', component: FormeditarComponent},
  { path: 'usuarios/formsuma', component: FormSumaPuntosComponent},
  { path: 'usuarios/formresta', component: FormRestaPuntosComponent},
  { path: 'productos', component: ProductosComponent },
  { path: 'existencias', component: ExistenciasComponent },
  { path: 'productos/form', component: FormComponentProducto },
  { path: 'usuarios/form', component: FormComponentUsuario },
  { path: 'usuarios/formeditar/:numct', component: FormeditarComponent },
  { path: 'usuarios/formbuscar/:numct', component: FormbuscarComponent},
  { path: 'productos/formbuscarproductos/:codigo', component: FormbuscarproductosComponent},
  { path: 'restablecer-contra', component: RestablecerContraComponent },
  { path: 'reportes', component: ReportesComponent },
  { path: 'mi-perfil', component: PerfilesComponent},
  { path: 'login', component: LoginComponent},
  { path: 'productos/formeditarp/:codigo', component: FormeditarPComponent},
  { path: 'rentas/renta', component: RentasComponent },
  { path: 'rentas/renta/:id', component: RentasComponent},
  { path: 'usuarios/form-suma-tarjeta/:numct', component: FormSumaTarjetaComponent},
  { path: 'usuarios/form-resta-tarjeta/:numct', component: FormRestaTarjetaComponent}
]

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    HistorialComponent,
    FooterComponent,
    ProductosComponent,
    UsuariosComponent,
    FormComponentProducto,
    FormComponentUsuario,
    ExistenciasComponent,
    FormeditarComponent,
    FormSumaPuntosComponent,
    FormRestaPuntosComponent,
    FormbuscarComponent,
    FormbuscarproductosComponent,
    RestablecerContraComponent,
    LoginComponent,
    FormeditarPComponent,
    FormSumaTarjetaComponent,
    FormRestaTarjetaComponent,
    ReportesComponent,
    RentasComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    RouterModule.forRoot(routes),
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
