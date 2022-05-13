package org.uc.Projeto2;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.uc.Classes.Jogo;

@RestController
public class AppController {
    @Autowired
    CartaoService cardService;

    @Autowired
    EquipaService teamService;

    @Autowired
    EventoService eventService;

    @Autowired
    GoloService goalService;

    @Autowired
    JogadorService playerService;

    @Autowired
    JogoService gameService;

    @Autowired
    UserService userService;


    // Jogos
    @GetMapping(value = "/", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Jogo> getGames(){
        return gameService.getAllGames();
    }

    @PostMapping(value = "/", produces = {MediaType.APPLICATION_JSON_VALUE})
    public void addGame(@RequestBody Jogo j){
        gameService.addGame(j);
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Jogo getGame(@PathVariable("id") int id){
        Optional<Jogo> op = gameService.getGame(id);
        if (op.isEmpty())
            return null;
        return op.get();
    }

    @PutMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public void editGame(@PathVariable("id") int id, @RequestBody Jogo j){
        Optional<Jogo> op = gameService.getGame(id);
        if (!op.isEmpty()){
            Jogo _j = op.get();
            _j.setNome(j.getNome());
            _j.setCurrGolosEquipaCasa(j.getCurrGolosEquipaCasa());
            _j.setCurrGolosEquipaFora(j.getCurrGolosEquipaFora());
            _j.setData_inicio(j.getData_inicio());
            _j.setData_fim(j.getData_fim());
            _j.setEstado(j.isEstado());
            _j.setLocalizacao(j.getLocalizacao());
            _j.setEquipas(j.getEquipas());
            _j.setEventos(j.getEventos());
            gameService.addGame(_j);
        }
    }

    @DeleteMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public void delGame(@PathVariable("id") int id){
        Optional<Jogo> op = gameService.getGame(id);
        if (op.isEmpty())
            return;
        gameService.delGame(op.get());
    }
}
