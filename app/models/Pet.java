package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;

@Entity
public class Pet extends Model{
    
    public String nome;
    public String idade;

    @ManyToOne
    public Pet pet;

    // @Enumerated(EnumType.STRING)
    // public Situacao situacao;

    // public Pet(){
    //     this.situacao = Situacao.ATIVA;
    // }

    public boolean isValid(){
        if (this.nome.isEmpty() || this.idade.isEmpty()) {
            return false;
            
        }
        return true;
    }

   

    
}
