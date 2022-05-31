package org.uc.Class;

import java.util.Date;

import javax.persistence.*;

@Entity (name = "Golo")
@Table(name = "Golo")
public class Golo extends SuperClassEventos {
    private Date data_golo;

    //Um jogador pode fazer vários golos e Um golo está associado a um jogador
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "jogador_id")
    private Jogador marcador;

    public Golo(Jogo jogo, Date data_golo, Jogador marcador) {
        super.setJogo(jogo);
        this.data_golo = data_golo;
        this.marcador = marcador;
    }

    public Golo(Date data_golo, Jogador marcador) {
        super();
        this.data_golo = data_golo;
        this.marcador = marcador;
    }

    public Golo() {
        super();
    }

    public Date getData_golo() {
        return data_golo;
    }

    public void setData_golo(Date data_golo) {
        this.data_golo = data_golo;
    }

    public Jogador getMarcador() {
        return marcador;
    }

    public void setMarcador(Jogador marcador) {
        this.marcador = marcador;
    }
}
