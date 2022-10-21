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

/**
 * Clase modelo para los usuarios. Esta clase es para el mapeo objeto-relacional
 * y por ende para la conexion con la base de datos.
 * 
 * @version 1.0
 */

// anotacciones de lombok para una clase con menos codigo
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
// anotaciones para hibernate
@Entity
@Table(name = "ctop.usuario")
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