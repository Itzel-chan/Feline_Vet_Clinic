package controllers;

import java.util.List;

import models.Pessoa;
import models.Pet;
import models.Situacao;
import play.mvc.Controller;

public class Pets extends Controller {

    public static void form(Long id) {

        if (id == null) {
            Pessoas.listar(null);
        }
        Pessoa pessoa = Pessoa.find("situacao = ?1 and id = ?2", Situacao.ATIVA, id).first();

        if (pessoa == null) {
            Pessoas.listar(null);
        }

        render(pessoa);
    }

    public static void salvar(Pet felino) {
        felino.save();
        listar(null);
    }

    public static void listar(String busca) {

        List<Pet> lista = null;

        if (busca == null || busca.trim().isEmpty()) {

            lista = Pet.find("situacao = ?1", Situacao.ATIVA).fetch();
        } else {

            lista = Pet.find("situacao = ?1 and lower(nome) like ?2", Situacao.ATIVA, "%" + busca.toLowerCase() + "%")
                    .fetch();
        }
        render(lista);

    }

    public static void editar(long id) {
        Pet fe = Pet.findById(id);
        Pessoa pessoa = fe.dono;
        renderTemplate("Pets/form.html", fe, pessoa);
    }

    public static void remover(long id) {
        Pet s = Pet.findById(id);
        s.situacao = Situacao.INATIVA;

        s.save();
        listar(null);
    }

}
