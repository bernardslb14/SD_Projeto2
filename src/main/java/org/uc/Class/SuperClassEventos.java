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
