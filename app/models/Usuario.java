package models;

import java.util.Date;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class Usuario extends Model{
    
    public String nome;
    public String email;
    public String senha;
    public Date dataNascimento;
    
}
