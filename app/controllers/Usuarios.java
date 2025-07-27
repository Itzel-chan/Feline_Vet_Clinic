package controllers;

import java.util.List;

import models.Usuario;
import play.mvc.Controller;

public class Usuarios extends Controller {

    public static void form() {
        render();
    }

    public static void salvar(Usuario usu) {
        usu.save();
        form();
    }

    public static void listar() {
        List<Usuario> lista = Usuario.findAll();
        render(lista);

    }

    public static void editar(long id) {
        Usuario usu = Usuario.findById(id);
        renderTemplate( "Usuarios/form.html, usu");
    }

    public static void remover() {
        
    
        

    }

}
