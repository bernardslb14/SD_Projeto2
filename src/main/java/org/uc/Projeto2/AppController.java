package org.uc.Projeto2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.uc.Classes.Equipa;
import org.uc.Classes.Jogador;
import org.uc.Classes.Jogo;
import org.uc.Classes.User;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
        String[] opcoes = {"Registar Utilizador", "Listar Utilizadores", "Criar Equipa", "Listar Equipas", "Inserir Jogador", "Listar Jogadores"};

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

        this.equipaService.addTeam(e);
        return "redirect:/displayAdmin";
    }

    @GetMapping("/listaEquipas")
    public String listaEquipas(Model m) {
        m.addAttribute("equipas", this.equipaService.getAllTeams());

        return "listaEquipas";
    }
}
