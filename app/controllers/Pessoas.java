package controllers;

import java.util.List;

import models.Pessoa;
import play.mvc.Controller;

public class Pessoas extends Controller {

    public static void form() {
        render();
    }

    public static void salvar(Pessoa pess) {
        pess.save();
        form();
    }

    public static void listar(String busca) {

        List<Pessoa> lista = null;

        if (busca != null) {
            lista = Pessoa.find("nome like ?1", "%" + busca + "%").fetch();

            
        }else{
            lista = Pessoa.findAll(); 
        }
        render(lista);





        // List<Usuario> lista;
        // if (busca == null) {
        //     lista = Usuario.findAll();

        // } else {

        //     lista = Usuario.find("nome like ?1 or email like ?1","%"+busca+"%").fetch();
        // }
        // render(lista);

    }

    public static void editar(long id) {
        Pessoa pe = Pessoa.findById(id);
        renderTemplate("Pessoas/form.html", pe);
    }

    public static void remover(long id) {
        Pessoa s = Pessoa.findById(id);
        s.delete();
        listar(null);

    }

}
