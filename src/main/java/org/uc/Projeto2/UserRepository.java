package org.uc.Projeto2;

import org.springframework.data.repository.CrudRepository;
import org.uc.Class.User;

public interface UserRepository extends CrudRepository<User, Integer>{
    
}
