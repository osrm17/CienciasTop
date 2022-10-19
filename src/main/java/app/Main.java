package app;

import controllers.UsuarioController;
import model.Usuario;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hola mundo.");
        Usuario usr = new Usuario("317180000", "6bf4969808f1c9998af7809f7aa2c3783aabf8451fe5a6f9d3c3b159fb31b91d",
                "Israel", "Gutierrez", "Elizalde", true, "isranchis@gmail.com", "5566651511", 100, false, false);
        UsuarioController c = new UsuarioController();
        System.out.println(c.add(usr));
    }
}