package app;

import model.Producto;
import model.ProductoDAO;
import model.ProductoDAOHibernate;
import model.Usuario;
import model.UsuarioDAO;
import model.UsuarioDAOHibernate;

public class OtrasPruebas {

    public static void main(String[] args) {
        testUsuario();
        testProducto();
    }

    public static void testUsuario() {
        Usuario usr = new Usuario("317180000", "6bf4969808f1c9998af7809f7aa2c3783aabf8451fe5a6f9d3c3b159fb31b91d",
                "Israel", "Gutierrez", "Elizalde", true, "isranchis@gmail.com", "5566651511", 100, false, false);
        System.out.println(usr.toString() + "\n\n\n");

        UsuarioDAO usuarioDAO = new UsuarioDAOHibernate();
        usuarioDAO.guardar(usr);
        System.out.println("\n\n\n");
        System.out.println(usuarioDAO.encontrar("317180000").toString());
        System.out.println("\n\n\n");

        for (Usuario u : usuarioDAO.encontrarTodos())
            System.out.println(u.toString());
    }

    public static void testProducto() {

        Producto producto = new Producto("172217221722", "317180000", "Currents - Tame impala", 20, 5,
                "Album Currents de la banda Tame Impala.");
        System.out.println(producto.toString() + "\n\n\n");

        ProductoDAO productoDAO = new ProductoDAOHibernate();
        productoDAO.guardar(producto);
        System.out.println("\n\n\n");
        System.out.println(productoDAO.encontrar("172217221722").toString());
        System.out.println("\n\n\n");

        for (Producto p : productoDAO.encontrarTodos())
            System.out.println(p.toString());
        productoDAO.borrar(producto);
        UsuarioDAO usuarioDAO = new UsuarioDAOHibernate();
        usuarioDAO.borrar(usuarioDAO.encontrar("317180000"));
    }
}
