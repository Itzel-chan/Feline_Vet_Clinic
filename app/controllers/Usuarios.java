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

    public static void listar(String busca) {

        List<Usuario> lista;
        if (busca == null) {
            lista = Usuario.findAll();

        } else {

            lista = Usuario.find("nome like ?1 or email like ?1","%"+busca+"%").fetch();
        }
        render(lista);

    }

    public static void editar(long id) {
        Usuario usu = Usuario.findById(id);
        renderTemplate("Usuarios/form.html", usu);
    }

    public static void remover(long id) {
        Usuario usu = Usuario.findById(id);
        usu.delete();
        listar(null);

    }

}
