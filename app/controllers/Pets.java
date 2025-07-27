package controllers;

import java.util.List;

import models.Pet;
import models.Usuario;
import play.mvc.Controller;

public class Pets extends Controller {

    public static void form() {
        render();
    }

    public static void salvar(Pet p, Long idUsuario) {
        if (idUsuario != null) {
            Usuario u = Usuario.findById(idUsuario);
            p.Usuarios.add(u);

        }

        p.save();
        editar(p.id);

    }

    public static void listar(String busca) {

        List<Pet> lista;
        if (busca == null) {
            lista = Pet.findAll();

        } else {
            lista = Pet.find("cast(valor as string) like ?1 order by valor", "%" + busca + "%").fetch();
        }
        render(lista, busca);
    }

    public static void editar(Long id) {
        Pet e = Pet.findById(id);
        List<Usuario> usuarios = Usuario.findAll();
        renderTemplate("Pets/forms.html", e, usuarios);
    }

    public static void remover(Long id) {

        Pet t = Pet.findById(id);
        t.delete();
        listar(null);

    }

    public static void removerUsuario(Long idPet, Long idUsu) {

        Pet e = Pet.findById(idPet);
        Usuario u = Usuario.findById(idUsu);

        e.Usuarios.remove(u);
        e.save();
        editar(e.id);

    }

}
