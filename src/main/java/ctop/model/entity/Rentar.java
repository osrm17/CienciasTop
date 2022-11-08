package ctop.model.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Clase modelo para los registros de rentas. Esta clase es para el mapeo
 * objeto-relacional y por ende para la conexion con la base de datos.
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
@Table(name = "rentar")
public class Rentar implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "numct")
    private String numct;

    @Column(name = "idexistencia")
    private long idExistencia;

    @Column(name = "fechadevolucion")
    private LocalDate fechaDevolucion;

    @Column(name = "fecharenta")
    private LocalDate fechaRenta;

    private static final long serialVersionUID = 1L;
}
