package controllers;

import java.util.List;

import models.Consulta;
import models.Pet;
import models.SituacaoConsulta;
import play.mvc.Controller;

public class Consultas extends Controller {

    public static void formConsulta(Long id) {
        Pet pet = Pet.findById(id);
        render(pet);

    }

    public static void registrarConsulta(Consulta consulta) {
        consulta.save();
        listarConsultas(null);
    }

    public static void agendar(Long id) {
        Consulta consulta = Consulta.findById(id);

        consulta.status = SituacaoConsulta.AGENDADA;

        consulta.save();
        listarConsultas(null);

    }

    public static void finalizarConsulta(Long id) {

        Consulta consulta = Consulta.findById(id);

        consulta.status = SituacaoConsulta.FINALIZADA;

        consulta.save();
        listarConsultas(null);

    }

    public static void listarConsultas(String termo) {
        List<Consulta> consultas = null;

        if (termo == null) {
            consultas = Consulta.findAll();
        }

        else if (termo.equals("andamento")) {
            consultas = Consulta.find("status = ?1, SituacaoConsulta.EM_ANDAMENTO").fetch();

        }
        else if (termo.equals("agendadas")) {
            consultas = Consulta.find("status = ?1, SituacaoConsulta.AGENDADA").fetch();

        }
        else if (termo.equals("finalizadas")) {
            consultas = Consulta.find("status = ?1, SituacaoConsulta.FINALIZADA").fetch();

        }

        render(consultas);

    }

}
