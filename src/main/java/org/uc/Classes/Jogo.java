package org.uc.Classes;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity (name = "Jogo")
public class Jogo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private Date data_inicio;
    private Date data_fim;
    private String localizacao;

    //A cada jogo estão associadas somente duas equipas
    @ManyToMany(mappedBy = "jogos")
    private List<Equipa> equipas;

    //Um evento está associado a um jogo e Um jogo tem vários eventos
    @OneToMany(cascade = CascadeType.ALL)
    private List<Evento> eventos;
}
