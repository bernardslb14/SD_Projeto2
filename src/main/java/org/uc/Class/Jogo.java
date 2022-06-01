package org.uc.Class;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity (name = "Jogo")
@Table(name = "Jogo")
public class Jogo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String nome;
    private int currGolosEquipaCasa;
    private int currGolosEquipaFora;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date data_inicio;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date data_fim;

    private boolean estado; // 0 -> Default | 1 -> Jogo Interrompido
    private String localizacao;

    //A cada jogo estão associadas somente duas equipas
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Equipa> equipas;


    //Um evento está associado a um jogo e Um jogo tem vários eventos
    @OneToMany(cascade = CascadeType.ALL)
    private List<Golo> golos;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Ocorrencia> ocorrencias;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Cartao> cartoes;

    public Jogo(String nome, int currGolosEquipaCasa, int currGolosEquipaFora, Date data_inicio, Date data_fim,
            boolean estado, String localizacao, List<Equipa> equipas, List<Golo> golos) {
        this.nome = nome;
        this.currGolosEquipaCasa = currGolosEquipaCasa;
        this.currGolosEquipaFora = currGolosEquipaFora;
        this.data_inicio = data_inicio;
        this.data_fim = data_fim;
        this.estado = estado;
        this.localizacao = localizacao;
        this.equipas = equipas;
        this.golos = golos;
    }

    public Jogo() {
        this.equipas = new ArrayList<Equipa>();
        this.golos = new ArrayList<Golo>();
        this.ocorrencias = new ArrayList<Ocorrencia>();
        this.cartoes = new ArrayList<Cartao>();
        this.currGolosEquipaCasa = 0;
        this.currGolosEquipaFora = 0;
        this.estado = false;
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

    public int getCurrGolosEquipaCasa() {
        return currGolosEquipaCasa;
    }

    public void setCurrGolosEquipaCasa(int currGolosEquipaCasa) {
        this.currGolosEquipaCasa = currGolosEquipaCasa;
    }

    public int getCurrGolosEquipaFora() {
        return currGolosEquipaFora;
    }

    public void setCurrGolosEquipaFora(int currGolosEquipaFora) {
        this.currGolosEquipaFora = currGolosEquipaFora;
    }

    public Date getData_inicio() {
        return data_inicio;
    }

    public void setData_inicio(Date data_inicio) {
        this.data_inicio = data_inicio;
    }

    public Date getData_fim() {
        return data_fim;
    }

    public void setData_fim(Date data_fim) {
        this.data_fim = data_fim;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public List<Equipa> getEquipas() {
        return equipas;
    }

    public void setEquipas(List<Equipa> equipas) {
        this.equipas = equipas;
    }

        public List<Golo> getGolos() {
        return golos;
    }

    public void setGolos(List<Golo> golos) {
        this.golos = golos;
    }

    public List<Ocorrencia> getOcorrencias() {
        return ocorrencias;
    }

    public void setOcorrencias(List<Ocorrencia> ocorrencias) {
        this.ocorrencias = ocorrencias;
    }

    public List<Cartao> getCartoes() {
        return cartoes;
    }

    public void setCartoes(List<Cartao> cartoes) {
        this.cartoes = cartoes;
    }

}
