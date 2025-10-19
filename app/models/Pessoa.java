package models;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.postgresql.translation.messages_bg;

import play.data.validation.Email;
import play.data.validation.Max;
import play.data.validation.Min;
import play.data.validation.MinSize;
import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class Pessoa extends Model {

    @Required(message = "Nome obrigatório!")
    public String nome;
   
    @Required(message = "Telefone obrigatório!")
    public String telefone;
   
    @Required
    @Email(message = "Fomato inválido!")
    public String email;
    
    @Required
    @MinSize(value= 4, message = "No mínimo 4 caracteres!")
    public String senha;
    
    public boolean IsAdm;

    @Enumerated(EnumType.STRING)
    public Situacao situacao;

    public Pessoa() {
        this.situacao = Situacao.ATIVA;
    }

}
