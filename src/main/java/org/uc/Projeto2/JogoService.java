package org.uc.Projeto2;    

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.uc.Classes.Jogo;

@Service    
public class JogoService   
{    
    @Autowired    
    private JogoRepository gameRepository;

    public List<Jogo> getAllGames()  
    {    
        List<Jogo> records = new ArrayList<>();    
        gameRepository.findAll().forEach(records::add);    
        return records; 
    }

    public void addGame(Jogo j)  
    {    
        gameRepository.save(j);    
    }

    public Optional<Jogo> getGame(int id) {
        return gameRepository.findById(id);
    }

    public void delGame(Jogo j){
        gameRepository.delete(j);
    }

}  