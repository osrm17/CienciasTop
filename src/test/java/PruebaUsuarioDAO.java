import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import model.Usuario;
import model.UsuarioDAO;
import model.UsuarioDAOHibernate;

/**
 * Clase para probar el objeto DAO para usuarios y asegurar que las operaciones
 * para acceder a la base de datos funcionan de manera correcta.
 * 
 * @version 1.0
 */
public class PruebaUsuarioDAO {

    private static UsuarioDAO usuariodDAOHibernate;
    private static Usuario[] usuariosPrueba;

    @BeforeAll
    public static void inicializacion() {
        usuariodDAOHibernate = new UsuarioDAOHibernate();
        usuariosPrueba = new Usuario[5];
        usuariosPrueba[0] = new Usuario("000000000", "6bf4969808f1c9998af7809f7aa2c3783aabf8451fe5a6f9d3c3b159fb31b91d",
                "Pepe", "Pruebas", "Unitarias", true, "pepe@gmail.com", "5500000000", 100, false, false);
        usuariosPrueba[1] = new Usuario("100000000", "6bf4969808f1c9998af7809f7aa2c3783aabf8451fe5a6f9d3c3b159fb31b91d",
                "Pepin", "Pruebas", "Unitarias", true, "pepin@gmail.com", "5500000001", 100, false, false);
        usuariosPrueba[2] = new Usuario("200000000", "6bf4969808f1c9998af7809f7aa2c3783aabf8451fe5a6f9d3c3b159fb31b91d",
                "Pepa", "Pruebas", "Unitarias", true, "pepa@gmail.com", "5500000002", 100, false, false);
        usuariosPrueba[3] = new Usuario("300000000", "6bf4969808f1c9998af7809f7aa2c3783aabf8451fe5a6f9d3c3b159fb31b91d",
                "Pepo", "Pruebas", "Unitarias", true, "pepo@gmail.com", "5500000003", 100, true, false);
        usuariosPrueba[4] = new Usuario("400000000", "6bf4969808f1c9998af7809f7aa2c3783aabf8451fe5a6f9d3c3b159fb31b91d",
                "Pepu", "Pruebas", "Unitarias", true, "pepu@gmail.com", "5500000004", 100, true, true);
    }

    @BeforeEach
    public void agregarUsuariosPrueba() {
        for (int i = 0; i < usuariosPrueba.length; i++)
            usuariodDAOHibernate.guardar(usuariosPrueba[i]);
    }

    @AfterEach
    public void borrarUsuariosPrueba() {
        for (int i = 0; i < usuariosPrueba.length; i++)
            usuariodDAOHibernate.borrar(usuariosPrueba[i]);
    }

    /**
     * Prueba unitaria para {@link UsuarioDAOHibernate#guardar(Usuario)}
     * Recordemos que este metodo devuelve true cuando ocurre un error al intentar
     * agregar a la bd.
     */
    @Test
    @DisplayName("guardar(Usuario usuario)")
    public void pruebaGuardar() {
        Usuario usuarioNuevo = new Usuario("317170000",
                "6bf4969808f1c9998af7809f7aa2c3783aabf8451fe5a6f9d3c3b159fb31b91d",
                "Alfred", "Montes", "Perez", true, "fred@gmail.com", "5566651511", 100, false, false);
        assertFalse(usuariodDAOHibernate.guardar(usuarioNuevo));
        assertTrue(usuariodDAOHibernate.guardar(usuarioNuevo)); // debe ocurrir error en guardar pues ya existe el usuario
        Usuario usr1 = usuariodDAOHibernate.encontrar(usuarioNuevo.getNumct());
        assertNotNull(usr1);
        assertEquals(usr1.toString(), usuarioNuevo.toString());
        assertFalse(usuariodDAOHibernate.borrar(usuarioNuevo));
    }

    @Test
    @DisplayName("encontrar(String llave)")
    public void pruebaEncontrar() {
        Usuario usuario = null;
        for (int i = 0; i < usuariosPrueba.length; i++) {
            usuario = usuariodDAOHibernate.encontrar(usuariosPrueba[i].getNumct());
            assertNotNull(usuario);
            assertEquals(usuario.toString(), usuariosPrueba[i].toString());
        }
    }

    @Test
    @DisplayName("borrar(Usuario usuario)")
    public void pruebaBorrar() {
        for (int i = 0; i < usuariosPrueba.length; i++)
            assertFalse(usuariodDAOHibernate.borrar(usuariosPrueba[i]));
        for (int i = 0; i < usuariosPrueba.length; i++)
            assertNull(usuariodDAOHibernate.encontrar(usuariosPrueba[i].getNumct()));
    }

    @Test
    @DisplayName("actualizar(Usuario usuario)")
    public void pruebaActualizar() {
        Usuario actualizado = new Usuario("000000000",
                "6bf4969808f1c9998af7809f7aa2c3783aabf8451fe5a6f9d3c3b159fb31b91d",
                "Pepote", "Pruebas", "Unitarias", true, "pepote@gmail.com", "5500000000", 100, false, false);
        assertFalse(usuariodDAOHibernate.actualizar(actualizado));
        Usuario usr1 = usuariodDAOHibernate.encontrar(actualizado.getNumct());
        assertNotNull(usr1);
        assertEquals(actualizado.toString(), usr1.toString());
    }

    @Test
    @DisplayName("encontrarTodos()")
    public void pruebaEncontrarTodos() {
        List<Usuario> lista = usuariodDAOHibernate.encontrarTodos();
        assertNotNull(lista);
        for (int i = 0; i < usuariosPrueba.length; i++)
            assertTrue(lista.contains(usuariosPrueba[i]));
    }
}