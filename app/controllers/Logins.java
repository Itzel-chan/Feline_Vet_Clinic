package controllers;

import models.Pessoa;
import models.Situacao;
import play.data.validation.Email;
import play.data.validation.Min;
import play.data.validation.MinSize;
import play.data.validation.Required;
import play.data.validation.Validation;
import play.mvc.Controller;

public class Logins extends Controller {

    public static void form() {
        render();
    }

    public static void logar(@Required(message = "Nome obrigatório") String nome,
            @Required(message = "Email obrigatório") @Email(message = "formatação inadequada") String email,
            @Required(message = "Senha obrigatória") @MinSize(value = 4, message = "Pelo menos 4 caracteres!") String senha) {

        if (Validation.hasErrors()) {
            validation.keep();
            flash.error("Falha ao logar!");
            form();

        }

        Pessoa pes = Pessoa.find("nome = ?1 and email = ?2 and senha = ?3 and situacao = ?4", nome, email, senha, Situacao.ATIVA).first();

        if (pes == null) {
            flash.error("nome, email ou senha incorreto(s)");
            form();
        }

        // Caso o usuario for um ADM, essa session vai existir
        if (pes.IsAdm == true) {
            session.put("adm", true);
        }

        // D pra todos os usuarios, sejam eles comuns ou ADMS
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
