package controllers;

import java.util.Date;

import models.Consulta;
import models.Pessoa;
import models.Pet;
import models.PetSexo;
import models.Situacao;
import models.SituacaoConsulta;
import play.jobs.Job;
import play.jobs.OnApplicationStart;

@OnApplicationStart
public class Inicializador extends Job {

    @Override
    public void doJob() throws Exception {
        if (Pessoa.count() == 0) {
            Pessoa adm1 = new Pessoa();
            adm1.nome = "adm";
            adm1.email = "adm@gmail.com";
            adm1.senha = "admm";
            adm1.situacao = Situacao.ATIVA;
            adm1.IsAdm = true;
            adm1.save();



              // Users
            Pessoa user1 = new Pessoa();
            user1.nome = "user1";
            user1.email = "user1@gmail.com";
            user1.senha = "user1";
            user1.IsAdm = false;
            user1.save();

            Pessoa user2 = new Pessoa();
            user2.nome = "user2";
            user2.email = "user2@gmail.com";
            user2.senha = "user2";
            user2.IsAdm = false;
            user2.save();

            // Pets
            Pet pet1 = new Pet();
            pet1.nome = "pet1";
            pet1.peso = 10.0;
            pet1.raca = "Bisnaga";
            pet1.sexo = PetSexo.MACHO;
            pet1.dataNascimento = new Date();
            pet1.dono = user1;
            pet1.save();

            Pet pet2 = new Pet();
            pet2.nome = "pet2";
            pet2.peso = 10.0;
            pet2.raca = "Salsicha";
            pet2.sexo = PetSexo.FEMEA;
            pet2.dataNascimento = new Date();
            pet2.dono = user1;
            pet2.save();

            Pet pet3 = new Pet();
            pet3.nome = "pet3";
            pet3.peso = 10.0;
            pet3.raca = "Salsicha";
            pet3.sexo = PetSexo.FEMEA;
            pet3.dataNascimento = new Date();
            pet3.dono = user2;
            pet3.save();

            Pet pet4 = new Pet();
            pet4.nome = "pet4";
            pet4.peso = 10.0;
            pet4.raca = "Salsicha";
            pet4.sexo = PetSexo.MACHO;
            pet4.dataNascimento = new Date();
            pet4.dono = user2;
            pet4.save();

            // Consultas
            Consulta consulta1 = new Consulta();
            consulta1.dataAgendada = new Date();
            consulta1.situacaoConsulta = SituacaoConsulta.AGENDADA;
            consulta1.pet = pet1;
            consulta1.save();

            Consulta consulta2 = new Consulta();
            consulta2.dataAgendada = new Date();
            consulta2.situacaoConsulta = SituacaoConsulta.FINALIZADA;
            consulta2.pet = pet2;
            consulta2.save();

            Consulta consulta3 = new Consulta();
            consulta3.dataAgendada = new Date();
            consulta3.pet = pet3;
            consulta3.save();

            Consulta consulta4 = new Consulta();
            consulta4.dataAgendada = new Date();
            consulta4.pet = pet4;
            consulta4.save();

        }
    }

}
