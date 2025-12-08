package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class Consulta extends Model {
    @Required
    public Date dataAgendada;
    
    @Required
    @ManyToOne
    public Pet pet;

    @Enumerated(EnumType.STRING)
    public SituacaoConsulta situacaoConsulta;

    public Consulta() {
        this.situacaoConsulta = situacaoConsulta.EM_ANDAMENTO;

    }

}
