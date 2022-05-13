package org.uc.Projeto2;    

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.uc.Classes.User;

@Service    
public class UserService
{    
    @Autowired    
    private UserRepository userRepository;

    public List<User> getAllUsers()  
    {    
        List<User> records = new ArrayList<>();    
        userRepository.findAll().forEach(records::add);    
        return records; 
    }

    public void addUser(User u)  
    {    
        userRepository.save(u);    
    }

    public Optional<User> getUser(int id) {
        return userRepository.findById(id);
    }

    public void delUser(User u){
        userRepository.delete(u);
    }

}  