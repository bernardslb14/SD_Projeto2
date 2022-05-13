package org.uc.Classes;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity (name = "Jogo")
public class Jogo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String nome;
    private int currGolosEquipaCasa;
    private int currGolosEquipaFora;
    private Date data_inicio;
    private Date data_fim;
    private boolean estado; // 0 -> Default | 1 -> Jogo Interrompido
    private String localizacao;

    //A cada jogo estão associadas somente duas equipas
    @ManyToMany(mappedBy = "jogos")
    private List<Equipa> equipas;

    //Um evento está associado a um jogo e Um jogo tem vários eventos
    @OneToMany(cascade = CascadeType.ALL)
    private List<Evento> eventos;



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

    public List<Evento> getEventos() {
        return eventos;
    }

    public void setEventos(List<Evento> eventos) {
        this.eventos = eventos;
    }
}
