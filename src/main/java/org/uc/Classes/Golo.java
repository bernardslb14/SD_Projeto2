package org.uc.Classes;

import javax.persistence.*;
import java.util.Date;

@Entity (name = "Golo")
@PrimaryKeyJoinColumn(name = "id")
public class Golo extends Evento {
    private Date data_golo;

    //Um jogador pode fazer vários golos e Um golo está associado a um jogador
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "jogador_id")
    private Jogador marcador;



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
