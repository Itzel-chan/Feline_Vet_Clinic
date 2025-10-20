package controllers;

import java.util.Arrays;
import java.util.List;

import models.Pessoa;
import models.Pet;
import models.PetSexo;
import models.Situacao;
import play.data.validation.Valid;
import play.mvc.Controller;
import play.mvc.With;

@With(Interceptador.class)
public class Pets extends Controller {

    public static void form() {
        List<PetSexo> sexos = Arrays.asList(PetSexo.values());
        render(sexos);
    }

    public static void salvar(@Valid Pet felino) {
        if (validation.hasErrors()) {
            validation.keep();
            form();
        }

        if (session.contains("DadosUsu") == false) {
            flash.error("Não logado");
            Logins.form();
        }

        String nomeUsu = session.get("DadosUsu");
        Pessoa donoPet = Pessoa.find("nome = ?1 ", nomeUsu).first();

        felino.dono = donoPet;
        felino.save();

        Pets.form();

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
        List<PetSexo> sexos = Arrays.asList(PetSexo.values());

        renderTemplate("Pets/form.html", fe, sexos);
    }

    public static void remover(long id) {
        Pet s = Pet.findById(id);
        s.situacao = Situacao.INATIVA;
        s.save();
        listar(null);
    }

    public static void listarPetsUsu() {
        if (session.contains("DadosUsu") == false) {
            flash.error("Login necessário!");
            Logins.form();
        }

        String NomeUsu = session.get("DadosUsu");
        Pessoa usuario = Pessoa.find("nome = ?1", NomeUsu).first();

        List<Pet> pets = Pet.find("dono = ?1", usuario).fetch();
        render(pets);

    }

}