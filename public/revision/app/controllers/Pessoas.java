package controllers;

import java.util.List;

import models.Pessoa;
import models.Situacao;
import play.data.validation.Valid;
import play.data.validation.Validation;
import play.mvc.Controller;
import play.mvc.With;

@With(Interceptador.class)
public class Pessoas extends Controller {
    public static void form() {
        // Form so renderiza (nos cruds simples. apenas isso mesmo)
        render();
    }

    // No salvar, sempre vamos receber um objeto model
    // Poderiamos receber Strings? poderiamos, mas naun é taun legal e tbm é
    // trabalhoso
    // Então, como é um controller de pessoas, eu vou receber uma pessoa pra salvar,
    // certo?
    public static void salvar(@Valid Pessoa pessoa) {

        if (Validation.hasErrors()) {
            validation.keep();
            form();
        }

        pessoa.save();
        flash.success("Pessoa salva com sucesso");
        form();
    }

    // O salvar, sempre vai ser assim, por mais q tenha outras maneiras e tals
    // Se é um controller de gatos, a funcao salvar recebe um gato, se é de
    // cachorros, um cachorro e etc
    // Capiche?

    // // O listar mais basico, lista tudo, enton, usamos o findAll
    // public static void listar() {
    // // Criamos uma lista do tipo do model (como é pessoa, o tipo é pessoa)
    // List<Pessoa> lista = Pessoa.findAll();
    // // E renderiza
    // render(lista);
    // }

    public static void listar() {
        // Estamos procurando as pessoa q tem o atributo entre as aspas sendo igual ao
        // primeiro argumento
        // Argumento é oq vem dps da virgula

        List<Pessoa> lista = Pessoa.find("situacao = ?1", Situacao.ATIVA).fetch();
        render(lista);
    }

    // Essas proximas funcoes, vao precisar receber um long id
    // Pq sao funcoes aonde é necessario saber especificamente qual pessoa vc vai
    // mexer
    // Usei pessoa, mas poderia ser pet, usuario, operador e etc

    // E so receber o id não é "pegar" a pessoa no banco de dados
    // Precisamos fazer uma pesquisa pelo id
    public static void detalhar(long id) {
        Pessoa pessoa = Pessoa.findById(id);
        // Nessa função, ou em funções semelhantes como: perfil e tals
        // Eu vou so renderizar essa pesquisa, ok?
        render(pessoa);
    }

    public static void editar(long id) {
        Pessoa pessoa = Pessoa.findById(id);
        // Para o editar, eu vou ter q mandar de volta para o form, então eu sempre uso
        // esse render
        renderTemplate("Pessoas/form.html", pessoa);
        // Dentro dos parenteses vai a URL (caminho) do arquivo html q eu quero mandar,
        // e dps da virgula, minha variavel
        // Q recebeu a pesquisa
    }

    // public static void remover(long id) {
    // Pessoa pissoa = Pessoa.findById(id);
    // // Oq eu vou fazer aqui, se chama "remoção literal", eu vou REALMENTE remover
    // // essa pessoa
    // // Do BD, diferentemente, da exclusão logica, aonde so simulamos isso.
    // Capiche?
    // // Pra isso, eu vou usar o delete, dps do nome da variavel

    // // Vou até mudar, pra vc ver

    // pissoa.delete(); // É o nome da variavel ponto delete, naun confundir com o
    // model Pessoa, ok?
    // // Feito issu, tudo terminado pra um crud simples.

    // // Proximo passo é a view
    // // Na vdd, como vc não vê, vou so mostrar o codigo, ok?

    // // Posso apagar os comentarios? Ou vc prefere q eu comente a função toda e
    // faça outra?
    // }

    public static void remover(long id) {
        Pessoa pessoa = Pessoa.findById(id);
        // Agr, vamos apenas mudar o valor do atributo situacao dele e atualizar no BD
        // com save()
        pessoa.situacao = Situacao.INATIVA;
        pessoa.save();
        listar();

        // Só mudamos o atributo dele, mas isso não termina a exclusão logica, pq nos
        // precisamos mexer
        // No listar, pois é aí q entra o "truque"

        // No vamos procurar, com find, as pessoas q tem situacao ATIVA e as q não tem,
        // vao ser "excluidas" dessa
        // listagem, por isso é exclusão logica

        // A gente não exclui de vdd, so não mostramos ele. Capiche?
    }

}
