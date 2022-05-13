package org.uc.Classes;

import javax.persistence.*;
import java.util.List;

@Entity(name = "Evento")
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    //Um jogo tem vários eventos e Um evento está associado a um jogo
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "jogo_id")
    private Jogo jogo;

    //Um golo está associado a um evento e Um evento tem vários golos
    @OneToMany(cascade = CascadeType.ALL)
    private List<Golo> listaGolos;
}




