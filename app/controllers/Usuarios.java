package controllers;

import java.util.List;

import models.Pet;
import models.Usuario;
import play.mvc.Controller;

public class Usuarios extends Controller {

    public static void form() {
        List<Pet> pets = Pet.findAll();

        render(pets);
    }

    public static void salvar(Usuario usu) {
        usu.save();
        editar(usu.id);
        // form();
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
        List<Pet> pets = Pet.findAll();
        renderTemplate("Usuarios/form.html", usu, pets);
    }

    public static void remover(long id) {
        Usuario usu = Usuario.findById(id);
        usu.delete();
        listar(null);

    }

}
