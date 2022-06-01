package org.uc.Projeto2;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.uc.Class.Golo;

@Service    
public class GoloService   
{    
    @Autowired    
    private GoloRepository goalRepository;

    public List<Golo> getAllGoals()  
    {    
        List<Golo> records = new ArrayList<>();    
        goalRepository.findAll().forEach(records::add);    
        return records; 
    }

    public void addGoal(Golo g)  
    {    
        goalRepository.save(g);    
    }

    public Optional<Golo> getGoal(int id) {
        return goalRepository.findById(id);
    }

    public void delGoal(Golo g){
        goalRepository.delete(g);
    }

    public int getBestScorer(){
        return goalRepository.findByGoals().get(0).getMarcador().getId();
    }
}  