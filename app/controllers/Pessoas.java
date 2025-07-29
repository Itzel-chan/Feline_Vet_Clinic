package controllers;

import java.util.List;

import models.Pessoa;
import models.Pet;
import models.Situacao;
import play.mvc.Controller;

public class Pessoas extends Controller {

    public static void form() {
        render();
    }

    public static void salvar(Pessoa pess) {
        pess.save();
        form();
    }

    public static void listar(String busca) {

        List<Pessoa> lista;

        if (busca == null || busca.trim().isEmpty()) {
            lista = Pessoa.find("situacao = ?1", Situacao.ATIVA).fetch();
        } else {
            lista = Pessoa.find("situacao = ?1 and lower(nome) like ?2", Situacao.ATIVA, "%" + busca + "%").fetch();
        }

        render(lista);
    }

    public static void editar(long id) {
        Pessoa pe = Pessoa.findById(id);
        renderTemplate("Pessoas/form.html", pe);
    }

    public static void remover(long id) {
        Pessoa s = Pessoa.findById(id);

        List<Pet> petsAssociados = Pet.find("situacao = ?1 and dono.id = ?2", Situacao.ATIVA, s.id).fetch();
        for (Pet pet : petsAssociados) {

            pet.situacao = Situacao.INATIVA;
            pet.save();
        }

        s.situacao = Situacao.INATIVA;
        s.save();
        listar(null);
    }

}
