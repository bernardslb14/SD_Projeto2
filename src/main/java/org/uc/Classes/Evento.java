package org.uc.Classes;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "Evento")
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    //Um jogo tem vários eventos e Um evento está associado a um jogo
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "jogo_id")
    private Jogo jogo;

    public Evento(Jogo jogo) {
        this.jogo = jogo;
    }

    public Evento() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Jogo getJogo() {
        return jogo;
    }

    public void setJogo(Jogo jogo) {
        this.jogo = jogo;
    }
}
