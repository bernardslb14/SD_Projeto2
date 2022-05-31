package org.uc.Projeto2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.uc.Class.*;


import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import org.json.*;


@Controller
public class AppController {
    @Autowired
    UserService userService;

    @Autowired
    JogadorService jogadorService;

    @Autowired
    EquipaService equipaService;

    @Autowired
    JogoService jogoService;

    @Autowired
    GoloService goloService;

    //...



    @GetMapping("/login")
    public void menu(Model m){
        m.addAttribute("user", new User());

    }



    @PostMapping("/checkUser")
    public String checkUser(@ModelAttribute User u) {
        List<User> lista = this.userService.getAllUsers();

        for(int i=0; i<lista.size(); i++){
            if((lista.get(i).getEmail().equals(u.getEmail())) && (lista.get(i).getPassword().equals(u.getPassword()))){
                if(lista.get(i).getIsAdmin()){
                    return "redirect:/displayAdmin";
                }
                else{
                    return "redirect:/displayUser";
                }
            }
        }

        return "redirect:/displayViewer";
    }

    @GetMapping("/displayAdmin")
    public void opcoesAdmin(Model m) {
        String[] opcoes = {"Registar Utilizador", "Listar Utilizadores",
                            "Criar Equipa", "Listar Equipas",
                            "Inserir Jogador", "Listar Jogadores",
                            "Introduzir Jogo", "Listar Jogos"};

        m.addAttribute("user", new User());
        m.addAttribute("opcoes", opcoes);

    }

    @PostMapping("/checkOptionAdmin")
    public String checkOptionAdmin(@ModelAttribute User u) {

        switch (u.getOpcao()){
            case "Registar Utilizador":
                return "redirect:/registaUtilizador";
            case "Listar Utilizadores":
                return "redirect:/listaUtilizadores";
            case "Criar Equipa":
                return "redirect:/criaEquipa";
            case "Listar Equipas":
                return "redirect:/listaEquipas";
            case "Inserir Jogador":
                return "redirect:/insereJogador";
            case "Listar Jogadores":
                return "redirect:/listaJogadores";
            case "Introduzir Jogo":
                return "redirect:/introduzJogo";
            case "Listar Jogos":
                return "redirect:/listaJogos";
        }

        return "redirect:/login";
    }



    @GetMapping("/registaUtilizador")
    public void registaUtilizador(Model m) {
        m.addAttribute("user", new User());

    }

    @PostMapping("/guardaUtilizador")
    public String guardaUtilizador(@ModelAttribute User u) {

        this.userService.addUser(u);
        return "redirect:/displayAdmin";
    }

    @GetMapping("/listaUtilizadores")
    public String listaUtilizadores(Model m) {
        m.addAttribute("users", this.userService.getAllUsers());

        return "listaUtilizadores";
    }


    @GetMapping("/insereJogador")
    public void insereJogador(Model m) {
        m.addAttribute("jogador", new Jogador());
        m.addAttribute("allEquipas", this.equipaService.getAllTeams());

    }

    @PostMapping("/guardaJogador")
    public String guardaJogador(@ModelAttribute Jogador j) {

        this.jogadorService.addPlayer(j);
        return "redirect:/displayAdmin";
    }

    @GetMapping("/listaJogadores")
    public String listaJogadores(Model m) {
        m.addAttribute("jogadores", this.jogadorService.getAllPlayers());

        return "listaJogadores";
    }


    @GetMapping("/criaEquipa")
    public void criaEquipa(Model m) {
        m.addAttribute("equipa", new Equipa());

    }

    @PostMapping("/guardaEquipa")
    public String guardaEquipa(@ModelAttribute Equipa e) {
        e.setVitorias(0);
        e.setEmpates(0);
        e.setDerrotas(0);

        this.equipaService.addTeam(e);

        return "redirect:/displayAdmin";
    }

    @GetMapping("/listaEquipas")
    public String listaEquipas(Model m) {
        m.addAttribute("equipas", this.equipaService.getAllTeams());

        return "listaEquipas";
    }


    @GetMapping("/introduzJogo")
    public void introduzJogo(Model m) {

        m.addAttribute("jogo", new Jogo());
        m.addAttribute("allEquipas", this.equipaService.getAllTeams());
    }

    @PostMapping("/guardaJogo")
    public String guardaJogo(@ModelAttribute Jogo j) {
        this.jogoService.addGame(j);

        return "redirect:/displayAdmin";
    }

    @GetMapping("/listaJogos")
    public String listaJogos(Model m) {
        m.addAttribute("jogos", this.jogoService.getAllGames());

        return "listaJogos";
    }



    @GetMapping("/displayUser")
    public String opcoesUser(Model m) {
        m.addAttribute("jogo", new Jogo());
        m.addAttribute("allJogos", this.jogoService.getAllGames());

        return "displayUser";
    }


    @GetMapping("/displayEventos")
    public String displayEventos(@ModelAttribute Jogo j, Model m){
        String[] opcoes = {"Inicio do Jogo", "Fim do jogo", "Golo",
                "Cartao Amarelo", "Cartao Vermelho",
                "Jogo Interrompido", "Jogo resumido"};

        SuperClassEventos eventos = new SuperClassEventos();
        eventos.setJogo(j);

        m.addAttribute("opcoes", opcoes);
        m.addAttribute("evento", eventos);
        m.addAttribute("jogo", j);

        return "displayEventos";
    }

    @GetMapping("/checkOptionUser")
    public String checkOptionUser(@ModelAttribute SuperClassEventos e, Model m) {

        switch (e.getTipo()) {
            case "Golo":
                return reportaGolo(e.getJogo(), m);
        }

        return "redirect:/login";
    }


    @GetMapping("/reportaGolo/{id}")
    public String reportaGolo(@PathVariable ("id") Jogo jogo, Model m) {
        Golo golo = new Golo();
        golo.setJogo(jogo);


        m.addAttribute("golo", golo);
        m.addAttribute("jogo", jogo);

        m.addAttribute("allJogadores", this.jogadorService.getAllPlayers());

        return "reportaGolo";
    }

    @PostMapping("/guardaGolo")
    public String guardaGolo(@ModelAttribute Golo g) {

        this.goloService.addGoal(g);
        return "redirect:/displayUser";
    }



    @GetMapping("/displayViewer")
    public void opcoesViewer(Model m) {
        String[] opcoes = {"Acompanhar Jogo", "Estatisticas"};

        m.addAttribute("user", new User());
        m.addAttribute("opcoes", opcoes);

    }

    @PostMapping("/checkOptionViewer")
    public String checkOptionViewer(@ModelAttribute User u) {

        switch (u.getOpcao()) {
            case "Acompanhar Jogo":
                return "redirect:/escolheJogo";
        }

        return "redirect:/login";
    }

    @GetMapping("/escolheJogo")
    public void detalhesJogo(Model m) {
        m.addAttribute("jogo", new Jogo());

        m.addAttribute("allJogos", this.jogoService.getAllGames());

    }

    @GetMapping("/detalhesJogo")
    public void detalhesJogo(@ModelAttribute Jogo j, Model m) {

        Optional<Jogo> aux = this.jogoService.getGame(j.getId());
        System.out.println(aux.get());

        if(aux.isPresent()){
            m.addAttribute("jogo", aux.get());
        }
        else{
            return;
        }
    }



    @PostMapping("/insertPLTeams")
    public String insertPLTeams(){
        try {
            JSONArray resp = Unirest.get("https://v3.football.api-sports.io/teams?league=39&season=2021")
                    .header("x-rapidapi-host", "v3.football.api-sports.io")
                    .header("x-rapidapi-key", "5655fb774ffe8e4605427c298b2df1b5")
                    .asJson()
                    .getBody()
                    .getObject()
                    .getJSONArray("response");

            for(int i = 0; i < resp.length(); i++){
                String teamName = resp.getJSONObject(i).getJSONObject("team").getString("name");
                String teamLogo = resp.getJSONObject(i).getJSONObject("team").getString("logo");

                Equipa e = new Equipa();
                e.setImagem(teamLogo);
                e.setNome(teamName);
                if (equipaService.getTeam(teamName) == null){
                    equipaService.addTeam(e);
                }
            }

        } catch (UnirestException e) {
            e.printStackTrace();
        }

        return "redirect:/listaEquipas";
    }

    @PostMapping("/insertPLPlayers")
    public String insertPLPlayers(){
        try {
            for(int page_num = 1; page_num <=40; page_num++){
                JSONArray resp = Unirest.get("https://v3.football.api-sports.io/players?league=39&season=2021&page="+ page_num )
                        .header("x-rapidapi-host", "v3.football.api-sports.io")
                        .header("x-rapidapi-key", "5655fb774ffe8e4605427c298b2df1b5")
                        .asJson()
                        .getBody()
                        .getObject()
                        .getJSONArray("response");

                for(int i = 0; i < resp.length(); i++){
                    Jogador j = new Jogador();

                    String playerName = resp.getJSONObject(i).getJSONObject("player").getString("name");
                    j.setNome(playerName);
                    String playerPosition = resp.getJSONObject(i).getJSONArray("statistics").getJSONObject(0).getJSONObject("games").getString("position");
                    j.setPosicao(playerPosition);
                    if (!resp.getJSONObject(i).getJSONObject("player").getJSONObject("birth").isNull("date")){
                        String playerDOB = resp.getJSONObject(i).getJSONObject("player").getJSONObject("birth").getString("date");
                        Date dob = new Date();
                        dob.setYear(Integer.parseInt(playerDOB.substring(0, 4)) - 1900);
                        dob.setMonth(Integer.parseInt(playerDOB.substring(5, 7)) - 1);
                        dob.setDate(Integer.parseInt(playerDOB.substring(8)));
                        j.setData_nascimento(dob);
                    }
                    String playerTeam = resp.getJSONObject(i).getJSONArray("statistics").getJSONObject(0).getJSONObject("team").getString("name");
                    Equipa eq = equipaService.getTeam(playerTeam);
                    j.setEquipa(eq);

                    if (jogadorService.getPlayer(playerName) == null || (jogadorService.getPlayer(playerName).getEquipa().getNome() != playerTeam)){
                        jogadorService.addPlayer(j);
                    }

                }
                if (page_num % 10 == 0){
                    Thread.sleep(60*1000);
                }
                System.out.println("Gathering Players (Page " + page_num + ")...");
            }
        } catch (UnirestException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "redirect:/listaJogadores";
    }

}