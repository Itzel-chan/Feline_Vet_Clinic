package models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

import play.data.validation.Min;
import play.data.validation.MinSize;
import play.data.validation.Required;
import play.data.validation.Unique;
import play.db.jpa.Model;

@Entity
public class Carro extends Model {

    // Lembresse q as anotações são assim @Required, @Min, @Max, @MinSize, @MaxSize
    // Vou colocar mais alguns atributos

    @Required(message = "Marca é obrigatória")
    @MinSize(value = 3, message = "No minimo, 3 caracteres")
    public String marca;
    @Required(message = "Peso é obrigatório")
    @Min(value = 100, message = "No minimo, 100 kg")
    public Double peso;
    @Required(message = "Série é obrigatória")
    @Min(value = 1, message = "a série não pode ser negativa")
    @Unique(message = "Esta série já está cadastrada")
    public Integer serie;

    // Feito as anotações, vamos pra o controller

    @Required
    @Enumerated(EnumType.STRING)
    public Situacao situacao;

    // agr entra parte da anotacao e atributo
    // E é necessario q vc saiba criar por vc mesma

    // Mas aqui vai a dica: faça sempre o "muito para um"

    // Como assim? simples, pense no relacionamento de um carro e uma pessoa

    // Muitas pessoa tem 1 carro em comum? ex: vc compra um carro, mas outra pessoa
    // tbm compra esse carro
    // Vcs dois dirigem o carro alternando? (a pessoa é um qq aleatorio)

    // Obviamente, não, vc compra um carro, ele é seu e ponto final, mesmo q VC
    // compartilhe com quem vc queira,
    // ainda é seu

    // Èntão, so 1 pessoa tem o carro, oq significa q deve ser apenas 1 pessoa no
    // relacionamento, certo?

    // Vimos pelo lado do carro, agr vamos olhar pelo lado da pessoa

    // Uma pessoa tem 1 carro e apenas 1? nop, no mundo real, não importa, 1 pessoa
    // pode ter varios carros, certo?

    // Do lado esquerdo antes do "To" se refere a classe onde estamos (carro) e o
    // lado direito a ota classe do rela
    // cionamento (Pessoa)

    @ManyToOne
    // Nome do atributo aqui
    public Pessoa proprietario;

    public Carro() {
        this.situacao = Situacao.ATIVA;
    }

}