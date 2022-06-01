package org.uc.Class;

import javax.persistence.*;

@MappedSuperclass
public class SuperClassEventos {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    //Um jogo tem vários eventos e Um evento está associado a um jogo
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "jogo_id")
    private Jogo jogo;

    private String tipo;


    public SuperClassEventos() {}

    public SuperClassEventos(String tipo) {
        this.tipo = tipo;
    }

    public SuperClassEventos(Jogo jogo) {
        this.jogo = jogo;
    }

    public SuperClassEventos(Jogo jogo, String tipo) {
        this.jogo = jogo;
        this.tipo = tipo;
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


    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
