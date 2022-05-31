package org.uc.Projeto2;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.uc.Class.Jogador;

@Service    
public class JogadorService   
{    
    @Autowired    
    private JogadorRepository playerRepository;

    public List<Jogador> getAllPlayers()  
    {    
        List<Jogador> records = new ArrayList<>();    
        playerRepository.findAll().forEach(records::add);    
        return records; 
    }

    public void addPlayer(Jogador j)  
    {    
        playerRepository.save(j);    
    }

    public Optional<Jogador> getPlayer(int id) {
        return playerRepository.findById(id);
    }

    public void delPlayer(Jogador j){
        playerRepository.delete(j);
    }


    public Jogador getPlayer(String s){
        List<Jogador> allPlayers = this.getAllPlayers();
        for(Jogador elem : allPlayers){
            if (elem.getNome().equals(s)){
                return elem;
            }
        }
        return null;
    }
}  