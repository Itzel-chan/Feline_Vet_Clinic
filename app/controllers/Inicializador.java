package controllers;

import models.Pessoa;
import models.Situacao;
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

        }
    }

}
