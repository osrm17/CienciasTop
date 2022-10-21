package extra;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Clase para tuplas.
 * 
 * @version 1.0
 */
@Getter
@Setter
@AllArgsConstructor
@ToString
public class Tupla<F, S> {
    private F primero;
    private S segundo;
}
