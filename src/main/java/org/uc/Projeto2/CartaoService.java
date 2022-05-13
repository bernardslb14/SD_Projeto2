package org.uc.Projeto2;    

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.uc.Classes.Cartao;

@Service    
public class CartaoService   
{    
    @Autowired    
    private CartaoRepository cardRepository;

    public List<Cartao> getAllCards()  
    {    
        List<Cartao>cardRecords = new ArrayList<>();    
        cardRepository.findAll().forEach(cardRecords::add);    
        return cardRecords; 
    }

    public void addCard(Cartao c)  
    {    
        cardRepository.save(c);    
    }

    public Optional<Cartao> getCard(int id) {
        return cardRepository.findById(id);
    }

    public void delCard(Cartao c){
        cardRepository.delete(c);
    }

}    