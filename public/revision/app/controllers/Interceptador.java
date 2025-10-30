package controllers;

import play.mvc.Before; // Cuidado com a importação, é sempre play.mvc
import play.mvc.Controller;

public class Interceptador extends Controller {

    // As funções dentro do interceptador não são public
    // Não precisa receber nada nos parenteses, apenas uma verificacao

    // Sempre before, para a função de verificar se o usu ta logado
    // Use sempre unless, pois queremos todas as funções sejam "vistoriadas"
    // menos (unless) algumas
    @Before(unless = { "Pessoas.form", "Pessoas.salvar", "Carros.form", "Carros.salvar", "Application.index" })
    static void usuarioLogado() {
        // Essa verificação, é se essa session, está criada
        // Vamos usar a chave da session

        // Use contains pra verificar se essa chave (dadosUsu) existe.
        // Se existe, é pq o usu tá logado.
        // Mas se for false, é pq não está
        if (session.contains("dadosUsu") == false) {
            // Só executa oq tiver aqui dentro, se o usu >>> NÃO <<<
            // estiver logado
            flash.error("Login necessário");
            Logins.form();
        }
    }

    // Diferentemente, doa função de cima, q é vendo se alguem está logado (independente
    // se for user comum ou um adm, esse naun vai ser usando unless, e sim only)
    @Before(only = {"Pessoas.remover"})
    static void adm(){
        // Mesmo esquema de cima, mas vamos verificar se a session de adm, 
        // esta criada, se sim, é pq o usu é um adm, mas se não, é um usu normal
        if (session.contains("ADM") == false) {
            flash.error("Ação restrita à ADMs");
            Logins.form();
        }
    }


}
