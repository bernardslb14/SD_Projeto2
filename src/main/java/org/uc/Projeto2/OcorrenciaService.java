package org.uc.Projeto2;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.uc.Class.Ocorrencia;

@Service    
public class OcorrenciaService   
{    
    @Autowired    
    private OcorrenciaRepository occurrenceRepository;

    public List<Ocorrencia> getAllOccurrences()  
    {    
        List<Ocorrencia> records = new ArrayList<>();    
        occurrenceRepository.findAll().forEach(records::add);    
        return records; 
    }

    public void addOccurrence(Ocorrencia o)  
    {    
        occurrenceRepository.save(o);    
    }

    public Optional<Ocorrencia> getOccurrence(int id) {
        return occurrenceRepository.findById(id);
    }

    public void delOccurrence(Ocorrencia o){
        occurrenceRepository.delete(o);
    }

}  