import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

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
import { RestablecerContraComponent } from './usuarios/restablecer-contra.component';
import { PerfilesComponent } from './perfiles/perfiles.component';
import { FormeditarPComponent } from './productos/formeditarp.component';


const routes: Routes = [
  { path: '', redirectTo: '', pathMatch: 'full' },
  { path: 'usuarios', component: UsuariosComponent },
  { path: 'productos', component: ProductosComponent},
  { path: 'existencias', component: ExistenciasComponent},
  { path: 'productos/form', component: FormComponentProducto},
  { path: 'usuarios/form', component: FormComponentUsuario},
  { path: 'usuarios/formeditar/:numct', component: FormeditarComponent},
  { path: 'usuarios/formsuma', component: FormSumaPuntosComponent},
  { path: 'usuarios/formresta', component: FormRestaPuntosComponent},
  { path: 'usuarios/formbuscar/:numct', component: FormbuscarComponent},
  { path: 'productos', component: ProductosComponent },
  { path: 'existencias', component: ExistenciasComponent },
  { path: 'productos/form', component: FormComponentProducto },
  { path: 'usuarios/form', component: FormComponentUsuario },
  { path: 'usuarios/formeditar/:numct', component: FormeditarComponent }, 
  { path: 'usuarios/formbuscar/:numct', component: FormbuscarComponent},
  { path: 'restablecer-contra', component: RestablecerContraComponent },
  { path: 'mi-perfil', component: PerfilesComponent},
  { path: 'productos/formeditarp/:codigo', component: FormeditarPComponent}
]

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
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
    RestablecerContraComponent,
    FormeditarPComponent
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
