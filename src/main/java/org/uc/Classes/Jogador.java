package org.uc.Classes;

import javax.persistence.*;
import java.util.Date;

@Entity (name = "Jogador")
public class Jogador {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String nome, posicao;
    private Date data_nascimento;


    //Uma equipa tem vários jogadores e Um jogador pertence a uma equipa
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipa_id") //Serve de Foreign Key da classe 'Equipa' para esta classe 'Jogador'
    private Equipa equipa;

}
