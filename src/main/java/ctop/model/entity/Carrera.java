package ctop.model.entity;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Clase modelo para los registros de las carreras de los usuarios.
 * Esta clase es para el mapeo objeto-relacional y por ende para
 * la conexion con la base de datos.
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
@Table(name = "carrera")
public class Carrera implements Serializable {

    @EmbeddedId
    CarreraId carreraId;

    private static final long serialVersionUID = 1L;
}
