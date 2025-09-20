package models;

import java.util.Date;
<<<<<<< HEAD

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
=======
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
>>>>>>> master

import play.db.jpa.Model;

@Entity
<<<<<<< HEAD
public class Pessoa extends Model{
    
    public String nome;
    public String email;
    public String senha;
    public Date dataNascimento;


    
=======
public class Pessoa extends Model {

    public String nome;
    public String telefone;
    public String email;
    public String senha;

    @Enumerated(EnumType.STRING)
    public Situacao situacao;

    public Pessoa() {
        this.situacao = Situacao.ATIVA;
    }

>>>>>>> master
}
