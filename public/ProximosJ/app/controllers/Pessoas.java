package controllers;

import java.util.List;

import models.Pessoa;
import play.mvc.Controller;
import play.mvc.With;

@With(Interceptador.class)
public class Pessoas extends Controller {

    public static void form() {
        render();
    }

    // Resto do código

    public static void salvar(Pessoa novaPessoa) {
        novaPessoa.save();
        flash.success("Usuário cadastrado com sucesso!");
        form();
    }

    public static void listar() {
        List<Pessoa> listaDePessoas = Pessoa.findAll();
        render(listaDePessoas);
    }

    public static void remover(long id) {
        flash.put("info", "Usuário removido com sucesso!");
        Pessoa pessoa = Pessoa.findById(id);
        pessoa.delete();
        listar();
    }

}
