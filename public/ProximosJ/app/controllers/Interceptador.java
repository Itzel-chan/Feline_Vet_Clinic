package controllers;

import play.mvc.Before;
import play.mvc.Controller;

public class Interceptador extends Controller {
    
    @Before(unless = {"Pessoas.form", "Pessoas.salvar"})
    static void monitoramento() {
        if (!session.contains("nomeDoUsuario")) {
            flash.error("É necessário estar logado para realizar esta ação!");
            Logins.form();
        }
    }

}
