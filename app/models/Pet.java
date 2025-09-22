package models;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;

@Entity
public class Pet extends Model {

    public String nome;
    public String raca;
    public Integer peso;
    public Date dataNascimento;

    @ManyToOne
    public Pessoa dono;

    @Enumerated(EnumType.STRING)
    public Situacao situacao;

    @Enumerated(EnumType.STRING)
    public PetSexo sexo;

    public Pet() {
        this.situacao = Situacao.ATIVA;
    }

    public int getIdade() {

        if (this.dataNascimento == null) {
            return 0;
        }
        LocalDate dataAtual = LocalDate.now();
        LocalDate dataNasci = this.dataNascimento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int idade = Period.between(dataAtual, dataNasci).getYears();
        return idade;
    }

}
