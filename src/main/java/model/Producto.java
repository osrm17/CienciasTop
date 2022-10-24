package model;

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
 * Clase modelo para los productos. Esta clase es para el mapeo objeto-relacional
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
@Table(name = "ctop.producto")
public class Producto {

    @Id
    @Column(name = "codigo")
    private String codigo;

    @Column(name = "numct")
    private String numct;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "costopuntos")
    private int costoPuntos;

    @Column(name = "diasrenta")
    private int diasRenta;

    @Column(name = "descripcion")
    private String descripcion;

}