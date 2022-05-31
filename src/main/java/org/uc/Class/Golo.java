package org.uc.Class;

import org.springframework.format.annotation.DateTimeFormat;


import java.util.Date;
import javax.persistence.*;


@Entity (name = "Golo")
@Table(name = "Golo")
public class Golo extends SuperClassEventos {
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date momento_golo;

    //Um jogador pode fazer vários golos e Um golo está associado a um jogador
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "jogador_id")
    private Jogador marcador;

    public Golo(Jogo jogo, Date momento_golo, Jogador marcador) {
        super.setJogo(jogo);
        this.momento_golo = momento_golo;
        this.marcador = marcador;
    }

    public Golo(Date momento_golo, Jogador marcador) {
        super();
        this.momento_golo = momento_golo;
        this.marcador = marcador;
    }

    public Golo() {
        super();
    }

    public Date getMomento_golo() {
        return momento_golo;
    }

    public void setMomento_golo(Date momento_golo) {
        this.momento_golo = momento_golo;
    }

    public Jogador getMarcador() {
        return marcador;
    }

    public void setMarcador(Jogador marcador) {
        this.marcador = marcador;
    }
}
