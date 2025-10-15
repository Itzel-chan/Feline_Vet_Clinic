package controllers;

import java.util.List;

import models.Pessoa;
import models.Pet;
import models.Situacao;
import play.mvc.Controller;
import play.mvc.With;

@With(Interceptador.class)

public class Pessoas extends Controller {

    public static void form() {
        render();
    }

    public static void salvar(Pessoa pess) {
        pess.save();
        flash.success("Usuário cadastrado com sucesso");
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

    public static void formConsulta(Long id) {
        Pet pet = Pet.findById(id);
        render(pet);

    }

    public static void registrarConsulta(Consulta consulta) {
        consulta.save();
        listar();
    }

    public static void agendar(Long id) {
        Consulta consulta = Consulta.findById(id);

        consulta.status = Status.AGENDADA;

        consulta.save();
        listar();

    }

    public static void finalizarConsulta(Long id) {

        Consulta consulta = Consulta.findById(id);

        consulta.status = Status.FINALIZADA;

        consulta.save();
        listar();

    }

    public void listarConsultas(String termo) {
        List<Consulta> consultas = null;

        if (termo.equals("andamento")) {
            consultas = Consulta.find("status = ?1, Status.EM_ANDAMENTO").fetch();

        }
        if (termo.equals("agendadas")) {
            consultas = Consulta.find("status = ?1, Status.AGENDADA").fetch();

        }
        if (termo.equals("finalizadas")) {
            consultas = Consulta.find("status = ?1, Status.FINALIZADA").fetch();

        }

        render(consultas);

    }

}
