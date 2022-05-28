package org.uc.Projeto2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.uc.Classes.Equipa;
import org.uc.Classes.Jogador;
import org.uc.Classes.Jogo;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("rest")
public class RESTcontroller {
    @Autowired
    JogadorService jogadorService;

    @Autowired
    EquipaService equipaService;

    @Autowired
    JogoService jogoService;

    //...



    //GET
    @GetMapping(value = "teams", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Equipa> getEquipas() {
        return equipaService.getAllTeams();
    }

    @GetMapping(value = "games", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Jogo> getJogos()
    {
        return jogoService.getAllGames();
    }


    //POST
    @PostMapping(value = "players", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public void addJogador(@RequestBody Jogador j) {
        jogadorService.addPlayer(j);
    }

    @PostMapping(value = "teams", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public void addEquipa(@RequestBody Equipa e) {
        equipaService.addTeam(e);
    }

    @PostMapping(value = "games", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public void addJogo(@RequestBody Jogo j) {
        jogoService.addGame(j);
    }

}
