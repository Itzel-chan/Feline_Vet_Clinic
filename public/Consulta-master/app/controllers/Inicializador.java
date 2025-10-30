package controllers;

import java.util.Date;

import models.Usuario;
import play.jobs.Job;
import play.jobs.OnApplicationStart;

// Essa essa classe junto com a função doJob ali (ksksksksk), vão ser acionadas assim que o play run ativar
@OnApplicationStart
public class Inicializador extends Job {

    public void doJob() {
        
        // Se não houver nenhum registro salvo de inicio (e claro que não vai ter), esse if executa o codigo dentro dele 
        if (Usuario.count() == 0) {

            // Criando manualemte um registro
            Usuario inicial1 = new Usuario();
            inicial1.nome = "Zé das brenha";
            inicial1.email = "ze@email.com";
            inicial1.cpf = "12345";
            inicial1.vinculoProfissao = "Autônomo";
            inicial1.dataNascimento = new Date(2000 - 1900, 0, 0);
            inicial1.quantidadeDePets = 10;
            // Salvando no BD
            inicial1.save();

            // Criando mais um registro manulamente
            Usuario inicial2 = new Usuario();
            inicial2.nome = "Tião da padaria";
            inicial2.email = "tiao@email.com";
            inicial2.cpf = "54321";
            inicial2.vinculoProfissao = "Funcionário Público";
            inicial2.dataNascimento = new Date(2010 - 1900, 5, 12);
            inicial2.quantidadeDePets = 1;
            // Salvando no BD
            inicial2.save();

            // Criando o ultimo registro na "mão"
            Usuario inicial3 = new Usuario();
            inicial3.nome = "Maquito das manga";
            inicial3.email = "maquito@email.com";
            inicial3.cpf = "98765";
            inicial3.vinculoProfissao = "Desempregado";
            inicial3.dataNascimento = new Date(1996 - 1900, 7, 8);
            inicial3.quantidadeDePets = 2;
            // Salvando no BD
            inicial3.save();

        }
    }
}
