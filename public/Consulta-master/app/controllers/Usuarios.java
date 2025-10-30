package controllers;

import java.util.List;

import models.Usuario;
import play.mvc.Controller;

public class Usuarios extends Controller {

    public static void form() {
        render();
    }

    public static void salvar(Usuario usu) {

        if (usu.validacao() == false || usu.duplicidade() == false) {
            form();
        }

        usu.save();
        listar();
    }

    public static void listar() {
        Integer totalDePets = 0;
        List<Usuario> lista = Usuario.findAll();

        // Fazendo um foreach para calcular a quantidade total de pets
        for (Usuario usu : lista) {
            totalDePets += usu.quantidadeDePets;
        }

        render(lista, totalDePets);
    }

    public static void remover(long id) {
        Usuario usu = Usuario.findById(id);
        usu.delete();
        listar();
    }

    public static void detalhar(long id) {
        Usuario usu = Usuario.findById(id);
        render(usu);
    }
}