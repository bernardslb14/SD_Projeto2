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

}
