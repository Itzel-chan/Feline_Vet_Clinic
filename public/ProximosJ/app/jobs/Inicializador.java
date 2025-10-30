package jobs;

import models.Pessoa;
import play.jobs.Job;
import play.jobs.OnApplicationStart;

@OnApplicationStart
public class Inicializador extends Job {

    @Override
    public void doJob() throws Exception {

        Pessoa p1 = new Pessoa();
        p1.nome = "José";
        p1.senha = "123";
        p1.save();

        Pessoa p2 = new Pessoa();
        p2.nome = "Maria";
        p2.senha = "321";
        p2.save();

        Pessoa p3 = new Pessoa();
        p3.nome = "Antônio";
        p3.senha = "132";
        p3.save();

    }
}
