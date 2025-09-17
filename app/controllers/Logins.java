package controllers;

import models.Pessoa;
import play.mvc.Controller;

public class Logins extends Controller {

    public static void form() {
        render();
    }

    public static void logar(String nome, String email, String senha) {

        if (nome == null || (nome != null && nome.trim().isEmpty()) ||
                email == null || (email != null && email.trim().isEmpty()) ||
                senha == null || (senha != null && senha.trim().isEmpty())) {

            flash.error("Nome, email e senha são obrigatórios!");
            form();
        }

        Pessoa pes = Pessoa.find("nome = ?1 and email = ?2 and senha = ?3", nome, email, senha).first();

        if (pes == null) {
            flash.error("nome, email ou senha incorreto(s)");
            form();
        }

        flash.success("Logado");
        session.put("DadosUsu", nome);
        Application.index();
    }

    public static void deslogar() {
        session.clear();
        flash.success("Deslogando");
        form();
    }

}
