package models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import play.data.validation.Unique;
import play.db.jpa.Model;

// Não esquecer da anotação
@Entity
public class Pessoa extends Model {

    // Sempre public
    

    // So colocar unique em cima, assim como @Required, e as outras
    // Unique -> unico (não pode ter dois atributos de duas pessoas com o mesmo
    // valor)
    @Unique
    public String nome;
    public String email;
    public String senha;

    // Atributo boolean pra diferenciar adm de usu comum
    public boolean isAdm;

    // Agr, precisamos criar o atributo ENUM aqui
    // Não esquecer da anotação
    @Enumerated(EnumType.STRING)
    public Situacao situacao;

    // Feito isso, basta modificarmos as funções listar e excluir

    public Pessoa() {
        this.situacao = Situacao.ATIVA;
    }

}
