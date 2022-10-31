package ctop.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Clase para la llave compuesta de la tabla carrera.
 * Esta clase es para el mapeo objeto-relacional
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
// hibernate jpa
@Embeddable
public class CarreraId implements Serializable {

    @Column(name = "numct")
    private String numct;

    @Column(name = "carrera")
    private String carrera;

    private static final long serialVersionUID = 1L;
}
