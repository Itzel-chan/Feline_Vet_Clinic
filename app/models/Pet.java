package models;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

import play.data.validation.Min;
import play.data.validation.MinSize;
import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class Pet extends Model {

    @Required(message = "Nome obrigatório!")
    @MinSize(value = 3, message = "No mínimo 3 caracteres!" )
    public String nome;

    @Required(message = "Raça obrigatória!")
    @MinSize(value = 5, message = "No mínimo 5 caracteres!")
    public String raca;

    @Required(message = "Peso obrigatório!")
    @Min(value = 0.1, message = "No mínimo 100g")
    public Double peso;

    @Required(message = "Data de Nascimento obrigatória!")
    public Date dataNascimento;

    @Required(message = "Campo obrigatório!")
    @Enumerated(EnumType.STRING)
    public PetSexo sexo;

    @ManyToOne
    public Pessoa dono;

    @Enumerated(EnumType.STRING)
    public Situacao situacao;

    public Pet() {
        this.situacao = Situacao.ATIVA;
    }

    public int getIdade() {

        if (this.dataNascimento == null) {
            return 0;
        }
        LocalDate dataAtual = LocalDate.now();
        LocalDate dataNasci = this.dataNascimento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int idade = Period.between(dataNasci,  dataAtual).getYears();

        return idade;
    }

}
