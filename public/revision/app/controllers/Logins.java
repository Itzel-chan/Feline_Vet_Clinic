package controllers;

import models.Pessoa;
import play.mvc.Controller;

public class Logins extends Controller {

    // Primeiro, o login tem 3 funções

    public static void form() {
        render();
    }

    // Na função logar, vamos precisar receber, plmns
    // 2 atributos do model.
    // Como vamos usar o model de Pessoa, vou usar o EMAIL e SENHA
    // Ambos são String
    public static void logar(String email, String senha) {
        // Essa parte, se chama autenticação.
        // Primeiro, vc cria um objeto do tipo do model (no nosso caso, pessoa)
        // E faz uma pesquisa usando oq vc recebeu como parametro
        Pessoa pessoa = Pessoa.find("email = ?1 and senha = ?2", email, senha).first();

        // A pesquisa tem duas posibilidades, pode ser null ou não
        // Caso for null, significa q não achou, se não for null, achou

        // Pra verificar, vamos usar um if

        if (pessoa == null) {
            // So entra nesse if, se caso as informações passadas no email e senha,
            // estiverem
            // incorretas
            flash.error("Email e/ou senha incorreto(s)");
            // Mando de volta pro form
            form();
        }

        // Se ele >>> NÃO <<< for null, o codigo ignora o if, e executa oq vem aqui
        // em baixo

        // Cria uma session, guardando alguma informação do usu
        // Como é so pra saber quem é, não precisa quebrar muito a cabeça
        // Só não use senha, please
        session.put("dadosUsu", pessoa.email);

        // Agr, vamos verificar, com if, se o usuario q logou é um adm
        if (pessoa.isAdm == true) {
            // Se for um ADM, vamos criar mais uma session
            session.put("ADM", true);

            // Somente issu
        }

        // Agr, so mandar uma msg de success e mandar pra alguma funcao

        flash.success("Usuário logado");
        Application.index();

    }

    public static void deslogar() {
        // O deslogar é bem simples
        // Basta limpar a session
        session.clear();
        flash.success("Usuário deslogado");
        form();
        // Só issu
    }

}
