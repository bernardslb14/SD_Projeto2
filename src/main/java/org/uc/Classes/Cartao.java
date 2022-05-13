package org.uc.Classes;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity(name = "Cartao")
@PrimaryKeyJoinColumn(name = "id")
public class Cartao extends Evento {
    private Date data_cartao;
    private boolean tipo; //True -> Amarelo | False -> Vermelho

    //Um jogador pode receber vários cartões (no máximo três: dois amarelos seguindo-se um vermelho) e Um cartão está associado a um jogador
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "jogador_id")
    private Jogador jogador;


    public Cartao(Jogo jogo, Date data_cartao, boolean tipo, Jogador jogador) {
        super(jogo);
        this.data_cartao = data_cartao;
        this.tipo = tipo;
        this.jogador = jogador;
    }

    public Cartao(Date data_cartao, boolean tipo, Jogador jogador) {
        super();
        this.data_cartao = data_cartao;
        this.tipo = tipo;
        this.jogador = jogador;
    }

    public Cartao() {
    }

    public Date getData_cartao() {
        return data_cartao;
    }

    public void setData_cartao(Date data_cartao) {
        this.data_cartao = data_cartao;
    }

    public boolean isTipo() {
        return tipo;
    }

    public void setTipo(boolean tipo) {
        this.tipo = tipo;
    }

    public Jogador getJogador() {
        return jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }
}
