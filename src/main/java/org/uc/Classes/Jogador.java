package org.uc.Classes;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity (name = "Jogador")
@Table(name = "Jogador")
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

    //Um jogador pode marcar vários golos e Um golo é feito por um jogador
    @OneToMany(mappedBy = "marcador")
    private List<Golo> golosJogador;

    //Um jogador pode ter vários cartões e Um cartão é atribuido a um jogador
    //É preciso?
    @OneToMany(mappedBy = "jogador")
    private List<Cartao> cartoesJogador;

    public Jogador(String nome, String posicao, Date data_nascimento, Equipa equipa, List<Golo> golosJogador,
            List<Cartao> cartoesJogador) {
        this.nome = nome;
        this.posicao = posicao;
        this.data_nascimento = data_nascimento;
        this.equipa = equipa;
        this.golosJogador = golosJogador;
        this.cartoesJogador = cartoesJogador;
    }

    public Jogador() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPosicao() {
        return posicao;
    }

    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }

    public Date getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(Date data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public Equipa getEquipa() {
        return equipa;
    }

    public void setEquipa(Equipa equipa) {
        this.equipa = equipa;
    }

    public List<Golo> getGolosJogador() {
        return golosJogador;
    }

    public void setGolosJogador(List<Golo> golosJogador) {
        this.golosJogador = golosJogador;
    }

    public List<Cartao> getCartoesJogador() {
        return cartoesJogador;
    }

    public void setCartoesJogador(List<Cartao> cartoesJogador) {
        this.cartoesJogador = cartoesJogador;
    }
}
