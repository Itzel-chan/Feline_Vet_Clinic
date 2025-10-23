package controllers;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import models.Consulta;
import models.Pet;
import models.SituacaoConsulta;
import play.cache.Cache;
import play.mvc.Controller;

public class Consultas extends Controller {

    public static void registrarConsulta(Date data, long id) {
        Consulta consulta = new Consulta();
        consulta.dataAgendada = data;

        Pet pet = Pet.findById(id);

        consulta.pet = pet;
        consulta.save();
        flash.success("Consulta registrada com sucesso!");

        listarConsultas(null);
    }

    public static void agendar(Long id) {
        Consulta consulta = Consulta.findById(id);

        consulta.situacaoConsulta = SituacaoConsulta.AGENDADA;

        consulta.save();
        listarConsultas(null);

    }

    public static void finalizarConsulta(Long id) {

        Consulta consulta = Consulta.findById(id);

        consulta.situacaoConsulta = SituacaoConsulta.FINALIZADA;

        consulta.save();
        listarConsultas(null);

    }

    public static void listarConsultas(String termo) {
        List<Consulta> consultas = null;

        if (termo == null) {
            consultas = Consulta.findAll();
        }

        else if (termo.equals("andamento")) {
            consultas = Consulta.find("situacaoConsulta = ?1, SituacaoConsulta.EM_ANDAMENTO").fetch();

        } else if (termo.equals("agendadas")) {
            consultas = Consulta.find("situacaoConsulta = ?1, SituacaoConsulta.AGENDADA").fetch();

        } else if (termo.equals("finalizadas")) {
            consultas = Consulta.find("situacaoConsulta = ?1, SituacaoConsulta.FINALIZADA").fetch();

        }

        render(consultas);

    }

    public static void calendario(Long id) {
        List<Date> datas = new ArrayList<>();
        Date data = null;
        LocalDate aux = null;
        
        int diasRestantesDoMes = LocalDate.now().lengthOfMonth() - LocalDate.now().getDayOfMonth();

        for (int i = 1; i <= diasRestantesDoMes; i++) {
            aux = LocalDate.now().plusDays(i);
            if (aux.getDayOfWeek()!= DayOfWeek.SATURDAY && aux.getDayOfWeek()!= DayOfWeek.SUNDAY) {
                data = Date.from(aux.atStartOfDay(ZoneId.systemDefault()).toInstant());
                datas.add(data);
            }
        }

        render(datas,id);

    }

}
