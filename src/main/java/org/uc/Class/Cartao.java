package org.uc.Class;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import javax.persistence.*;

@Entity(name = "Cartao")
@Table(name = "Cartao")
public class Cartao extends SuperClassEventos{

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date momento_cartao;

    //Um jogador pode receber vários cartões (no máximo três: dois amarelos seguindo-se um vermelho) e Um cartão está associado a um jogador
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "jogador_id")
    private Jogador jogador;


    public Cartao(Jogo jogo, Date momento_cartao, String tipo, Jogador jogador) {
        super(jogo, tipo);
        this.momento_cartao = momento_cartao;
        this.jogador = jogador;
    }

    public Cartao(Date momento_cartao, String tipo, Jogador jogador) {
        super(tipo);
        this.momento_cartao = momento_cartao;
        this.jogador = jogador;
    }

    public Cartao() {
        super();
    }

    public Date getMomento_cartao() {
        return momento_cartao;
    }

    public void setMomento_cartao(Date momento_cartao) {
        this.momento_cartao = momento_cartao;
    }


    public Jogador getJogador() {
        return jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }
}
