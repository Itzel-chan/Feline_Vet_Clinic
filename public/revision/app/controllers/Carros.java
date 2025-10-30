package controllers;

import java.util.List;

import models.Carro;
import models.Pessoa;
import models.Situacao;
import play.cache.Cache;
import play.data.validation.Valid;
import play.data.validation.Validation;
import play.mvc.Controller;
import play.mvc.With;

// Anote a classe com isso, todas as classes q vc quer proteger
@With(Interceptador.class)
public class Carros extends Controller {
    public static void form() {
        // Para podermos escolher a pessoa no form
        // Vamos criar uma lista de pessoas e mandar para o html

        List<Pessoa> pessoas = Pessoa.find("situacao = ?1", Situacao.ATIVA).fetch();

        // Cria uma variavel
        // E joga o carro q tá no cache, pra dentro da variavel
        Carro carro = (Carro) Cache.get("Carro");

        // Limpa o cache
        Cache.clear();
        // E manda o carro tbm

        // Importante lembrar q o nome da variavel, tem q ser o nome q tá dentro do
        // value nos inputs do form

        // tem q ser igual, se não, não funciona

        render(pessoas, carro);
    }

    // A validacao vai ser sempre a mesma, e ainda mais, sempre na função salvar
    public static void salvar(@Valid Carro carro) {

        // E é só issu, a unica coisa q muda é a msg no flash e pra qual função vc quer
        // mandar
        if (Validation.hasErrors()) {
            validation.keep();
            flash.error("Falha ao salvar o carro, tente novamente");

            // Para usar o cache, vamos criar ele aqui dentro desse if, pois vamos guardar
            // as informações do carro, caso dê errado o cadastro
            // E coloca as informaçoes dentro dos inputs dnv
            Cache.add("Carro", carro);

            // Feito issu, vamos pega-lo no form

            form();
        }

        // Feito isso, so falta criar os spans no form.html

        carro.save();
        flash.success("Carro salva com sucesso");
        listar();
    }

    public static void listar() {
        List<Carro> lista = Carro.find("situacao = ?1", Situacao.ATIVA).fetch();
        render(lista);
    }

    public static void detalhar(long id) {
        Carro carro = Carro.findById(id);
        render(carro);
    }

    public static void editar(long id) {
        Carro carro = Carro.findById(id);
        renderTemplate("Carros/form.html", carro);
    }

    public static void remover(long id) {
        Carro carro = Carro.findById(id);
        carro.situacao = Situacao.INATIVA;
        carro.save();
        listar();
    }

}
