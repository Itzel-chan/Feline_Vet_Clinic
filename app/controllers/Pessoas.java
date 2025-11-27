package controllers;

import java.util.List;

import models.Consulta;
import models.Pessoa;
import models.Pet;
import models.Situacao;
import play.data.validation.Valid;
import play.mvc.Controller;
import play.mvc.With;

@With(Interceptador.class)

public class Pessoas extends Controller {

    public static void form() {
        render();
    }

    public static void salvar(@Valid Pessoa pess) {
        if (validation.hasErrors()) {
            validation.keep();
            form();

        }
        pess.save();
        flash.success("Usuário cadastrado com sucesso");
        Logins.form();
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

        List<Consulta> consuPet = null;
        for (Pet pet : petsAssociados) {

            consuPet = Consulta.find("pet = ?1", pet).fetch();
        for (Consulta consulta : consuPet) {
                consulta.delete();
            }

            pet.situacao = Situacao.INATIVA;
            pet.save();
        }

        s.situacao = Situacao.INATIVA;
        s.save();
        listar(null);
    }

    public static void perfil(){
        String chave = session.get("DadosUsu");
        Pessoa pessoa = Pessoa.find("nome = ?1", chave).first();
        render(pessoa);
    }

}
