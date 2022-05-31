package org.uc.Projeto2;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.uc.Class.Evento;

@Service    
public class EventoService   
{    
    @Autowired    
    private EventoRepository eventRepository;

    public List<Evento> getAllEvents()  
    {    
        List<Evento> records = new ArrayList<>();    
        eventRepository.findAll().forEach(records::add);    
        return records; 
    }

    public void addEvent(Evento e)  
    {    
        eventRepository.save(e);    
    }

    public Optional<Evento> getEvent(int id) {
        return eventRepository.findById(id);
    }

    public void delEvent(Evento e){
        eventRepository.delete(e);
    }

}  