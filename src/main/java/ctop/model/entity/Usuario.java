package ctop.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
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
@EqualsAndHashCode
// anotaciones para hibernate
@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

    @Id
    @Column(name = "numct", nullable=false, unique=true, length=9)
    private String numct;

    @Column(name = "contrasenia", nullable=false, length=64)
    private String contrasenia;

    @Column(name = "nombre", nullable=false, length=50)
    private String nombre;

    @Column(name = "paterno", nullable=false, length=50)
    private String paterno;

    @Column(name = "materno", nullable=false, length=50)
    private String materno;

    @Column(name = "estaactivo", nullable=false)
    private boolean estaActivo;

    @Column(name = "correo", nullable=false, length=60)
    private String correo;

    @Column(name = "celular", nullable=false, length=10)
    private String celular;

    @Column(name = "pumapuntos", nullable=false)
    private int pumaPuntos;

    @Column(name = "esproveedor", nullable=false)
    private boolean esProveedor;

    @Column(name = "esadministrador", nullable=false)
    private boolean esAdministrador;

    private static final long serialVersionUID = 1L;
}