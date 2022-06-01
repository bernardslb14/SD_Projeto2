package org.uc.Class;

import org.springframework.format.annotation.DateTimeFormat;


import java.util.Date;
import javax.persistence.*;


@Entity (name = "Ocorrencia")
@Table(name = "Ocorrencia")
public class Ocorrencia extends SuperClassEventos {
    @DateTimeFormat(pattern = "HH:mm")
    private Date momento;

    public Ocorrencia(String tipo, Date momento) {
        super(tipo);
        this.momento = momento;
    }

    public Ocorrencia(Jogo jogo, Date momento) {
        super(jogo);
        this.momento = momento;
    }

    public Ocorrencia(Jogo jogo, String tipo, Date momento) {
        super(jogo, tipo);
        this.momento = momento;
    }

    public Ocorrencia(Jogo jogo, String tipo) {
        super(jogo, tipo);
    }

    public Ocorrencia(){
        super();
    }

    public Date getMomento() {
        return momento;
    }

    public void setMomento(Date momento) {
        this.momento = momento;
    }
}
