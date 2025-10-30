package controllers;

import models.Pessoa;
import play.mvc.Controller;

public class Logins extends Controller {

    public static void form() {
        render();
    }

    public static void login(String nome, String senha) {
        Pessoa pessoa = Pessoa.find("nome = ?1 and senha = ?2", nome, senha).first();

        if (pessoa == null) {
            flash.error("Nome e/ou senha incorretos!");
            form();
        }

        session.put("nomeDoUsuario", nome);
        flash.success("Login bem sucessido! Boas vindas " + nome);
        Application.index();
    }

    public static void logout() {
        flash.put("info", "Logout, até logo!");
        session.clear();
        form();
    }
}
