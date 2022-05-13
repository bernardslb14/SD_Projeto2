package org.uc.Classes;

import javax.persistence.*;
import java.util.List;

@Entity (name = "Equipa")
public class Equipa {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String nome, imagem;
    private int vitorias, empates, derrotas;

    //Um jogador só pertence a uma equipa e Uma equipa tem vários jogadores
    /*
        O mappedBy utiliza-se no lado não dominante da relação de modo a gerirmos os relacionamentos criados da melhor forma.
        A classe 'Equipa' é o lado dominante, pelo que temos de fazer uma referência na classe 'Jogo' usando o mappedBy e assim
        cria-se uma associação bidirecional ao invés de termos várias relações unidirecionais.

     */
    @OneToMany(mappedBy = "equipa")
    private List<Jogador> jogadoresEquipa;

    //Cada equipa vai ter uma lista de jogos associados
    /*
        Sem a classe 'Equipa', a classe 'Jogo' não tem qualquer significado. Logo utiliza-se o CascadeType para propagar
        as ações feitas nesta classe para aquela lhe está associada.
            Ex: Se eliminarmos uma equipa, os jogos que lhe estão associados também vão ser eliminados.
     */
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Jogo> jogos;

    public Equipa(String nome, String imagem, int vitorias, int empates, int derrotas, List<Jogador> jogadoresEquipa,
            List<Jogo> jogos) {
        this.nome = nome;
        this.imagem = imagem;
        this.vitorias = vitorias;
        this.empates = empates;
        this.derrotas = derrotas;
        this.jogadoresEquipa = jogadoresEquipa;
        this.jogos = jogos;
    }

    public Equipa() {
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

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public List<Jogador> getJogadoresEquipa() {
        return jogadoresEquipa;
    }

    public void setJogadoresEquipa(List<Jogador> jogadoresEquipa) {
        this.jogadoresEquipa = jogadoresEquipa;
    }

    public List<Jogo> getJogos() {
        return jogos;
    }

    public void setJogos(List<Jogo> jogos) {
        this.jogos = jogos;
    }

    public int getVitorias() {
        return vitorias;
    }

    public void setVitorias(int vitorias) {
        this.vitorias = vitorias;
    }

    public int getEmpates() {
        return empates;
    }

    public void setEmpates(int empates) {
        this.empates = empates;
    }

    public int getDerrotas() {
        return derrotas;
    }

    public void setDerrotas(int derrotas) {
        this.derrotas = derrotas;
    }
}
