package models;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class Pessoa extends Model {

    public String nome;
    public String email;

    @Enumerated(EnumType.STRING)
    public Situacao situacao;

    public Pessoa() {
        this.situacao = Situacao.ATIVA;
    }

}
