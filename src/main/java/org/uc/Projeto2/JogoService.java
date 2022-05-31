package org.uc.Projeto2;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.uc.Class.Equipa;
import org.uc.Class.Jogo;

@Service    
public class JogoService   
{    
    @Autowired    
    private JogoRepository gameRepository;

    @Autowired EquipaService equipaService;

    public List<Jogo> getAllGames()  
    {    
        List<Jogo> records = new ArrayList<>();    
        gameRepository.findAll().forEach(records::add);    
        return records; 
    }

    public void addGame(Jogo j)
    {
        /*
        Jogo novoJogo = new Jogo();

        novoJogo.setNome(j.getNome());
        novoJogo.getEquipas().addAll(j.getEquipas()
                .stream()
                .map(e -> { Equipa ee = equipaService.getTeam(e.getId());
                            ee.getJogos().add(novoJogo);
                            return ee;
                            }).collect(Collectors.toList()));
        */

        gameRepository.save(j);
    }

    public Optional<Jogo> getGame(int id) {
        return gameRepository.findById(id);
    }

    public void delGame(Jogo j){
        gameRepository.delete(j);
    }

}  