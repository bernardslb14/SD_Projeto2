package org.uc.Projeto2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.uc.Class.Equipa;
import org.uc.Class.Jogador;
import org.uc.Class.Jogo;
import org.uc.Class.User;

import java.util.List;
import java.util.Optional;

//@RestController
//@RequestMapping("rest")
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

    //...



    @GetMapping("/login")
    public void menu(Model m){
        m.addAttribute("user", new User());

    }

    @PostMapping("/checkUser")
    public String checkUser(@ModelAttribute User u) {
        List<User> lista = this.userService.getAllUsers();

        for(int i=0; i<lista.size(); i++){
            if((lista.get(i).getNome().equals(u.getNome())) && (lista.get(i).getPassword().equals(u.getPassword()))){
                if(lista.get(i).getIsAdmin()){
                    return "redirect:/displayAdmin";
                }
                else{
                    return "redirect:/displayUser"; //...
                }
            }
        }

        return "redirect:/login";
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
    public String checkOption(@ModelAttribute User u) {

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
        Jogo jogo = new Jogo();
        Equipa A = new Equipa();
        Equipa B = new Equipa();

        List<Equipa> equipas = List.of(A, B);

        jogo.setEquipas(equipas);
        jogo.setCurrGolosEquipaCasa(0);
        jogo.setCurrGolosEquipaFora(0);
        jogo.setEstado(false);

        m.addAttribute("jogo", jogo);
        m.addAttribute("allEquipas", this.equipaService.getAllTeams());
    }

    @PostMapping("/guardaJogo")
    public String guardaJogo(@ModelAttribute Jogo j) {
        System.out.println(j.getEquipas().get(0));

        this.jogoService.addGame(j);

        return "redirect:/displayAdmin";
    }

    @GetMapping("/listaJogos")
    public String listaJogos(Model m) {
        m.addAttribute("jogos", this.jogoService.getAllGames());

        return "listaJogos";
    }
}
