package org.uc.Classes;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "Evento")
@Table(name = "Evento")
public class Evento extends SuperClassEventos {

    public Evento(Jogo jogo) {
        super.setJogo(jogo);
    }

    public Evento() {
    }
}
