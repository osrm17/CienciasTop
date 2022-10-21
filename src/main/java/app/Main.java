package app;

import model.Usuario;
import model.UsuarioDAOImplHibernate;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hola mundo.");
        Usuario usr = new Usuario("317180000", "6bf4969808f1c9998af7809f7aa2c3783aabf8451fe5a6f9d3c3b159fb31b91d",
                "Israel", "Gutierrez", "Elizalde", true, "isranchis@gmail.com", "5566651511", 100, false, false);
        System.out.println(usr.toString() + "\n\n\n");
        UsuarioDAOImplHibernate uDAOimp = new UsuarioDAOImplHibernate();
        uDAOimp.guardar(usr);
        System.out.println("\n\n\n");
        System.out.println(uDAOimp.encontrar("317180000").toString());
        System.out.println("\n\n\n");
        for (Usuario u : uDAOimp.encontrarTodos())
            System.out.println(u.toString());
    }
}