package org.uc.Projeto2;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.uc.Class.Equipa;

@Service    
public class EquipaService   
{    
    @Autowired    
    private EquipaRepository teamRepository;

    public List<Equipa> getAllTeams()  
    {    
        List<Equipa> records = new ArrayList<>();    
        teamRepository.findAll().forEach(records::add);    
        return records; 
    }

    public void addTeam(Equipa e)  
    {    
        teamRepository.save(e);    
    }

    public Optional<Equipa> getTeam(int id) {
        return teamRepository.findById(id);
    }

    public void delTeam(Equipa e){
        teamRepository.delete(e);
    }


    public Equipa getTeam(String s){
        List<Equipa> allTeams = this.getAllTeams();
        for(Equipa elem : allTeams){
            if (elem.getNome().equals(s)){
                return elem;
            }
        }
        return null;
    }
}  