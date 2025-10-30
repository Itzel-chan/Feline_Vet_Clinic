package models;

import java.time.Year;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Transient;

import play.db.jpa.Model;

@Entity
public class Usuario extends Model {

    public String nome;
    public String email;
    public String cpf;
    public String vinculoProfissao;
    public Date dataNascimento;
    public Integer quantidadeDePets;
    @Transient // Se cair um atributo idade, coloque esse arroba em cima dele do jeito que tá aí
    public Integer idade;

    // Validação de dados
    public boolean validacao() {

        if (this.nome.isEmpty() || this.cpf.isEmpty() || this.cpf.isEmpty() || this.vinculoProfissao.isEmpty() // Validação de Strings
                || this.dataNascimento == null || this.dataNascimento.after(new Date()) // Validação de tipo Date
                || this.quantidadeDePets == null || this.quantidadeDePets < 0) { // Validação de Integer/Double (Com D maisculo)
                    // Sysout se vê na tela preta que escreve play run
                    System.out.println("\nErro ao tentar salvar!!!");
                    return false;
        }

        System.out.println("\nTudo certo na validação");
        return true;
    }

    // Teste de duplicidade
    public boolean duplicidade() {
        Usuario usu = Usuario.find("cpf = ?1", this.cpf).first();

        if (usu != null) {
        System.out.println("\nEsse CPF já está registrado!!!");
        return false;
    }
    
    System.out.println("\nTudo certo no teste de duplicidade, salvando novo CPF");
        return true;
    }

    // Para calcular a idade dinamicamente
    // Só copia e cola isso, caso precise
    public Integer getIdade() {
        Calendar calendarDataAtual = Calendar.getInstance();
        Calendar calendarDataNascimento = Calendar.getInstance();

        calendarDataNascimento.setTime(this.dataNascimento);

        int dataAtual = calendarDataAtual.get(Calendar.YEAR);
        int dataNascimento = calendarDataNascimento.get(Calendar.YEAR);

        Integer idade = dataAtual - dataNascimento;

        return idade;
    }

}
