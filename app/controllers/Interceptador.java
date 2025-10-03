package controllers;

import play.mvc.Before;
import play.mvc.Controller;

public class Interceptador extends Controller {

    @Before(unless ={"Pessoas.form", "Pessoas.salvar", "Pets.salvar"})
    static void monitoramento(){
        if(!session.contains("DadosUsu")){
            flash.error("É necessário estar logado!");
            Logins.form();

        }
    }
    @Before(only ={"Pessoas.listar", "Pets.listar"})
    static void acessoAdm(){
        if(!session.contains("adm")){
            flash.error("Restrito a administradores!");
            Logins.form();

        }
    }
    
}
