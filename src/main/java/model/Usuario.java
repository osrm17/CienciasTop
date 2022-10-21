package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "ctop.usuario")
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    @Id
    @Column(name = "numct")
    private String numct;

    @Column(name = "contrasenia")
    private String contrasenia;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "paterno")
    private String paterno;

    @Column(name = "materno")
    private String materno;

    @Column(name = "estaactivo")
    private boolean estaActivo;

    @Column(name = "correo")
    private String correo;

    @Column(name = "celular")
    private String celular;

    @Column(name = "pumapuntos")
    private int pumaPuntos;

    @Column(name = "esproveedor")
    private boolean esProveedor;

    @Column(name = "esadministrador")
    private boolean esAdministrador;
}