package org.uc.Projeto2;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.uc.Class.Golo;

public interface GoloRepository extends CrudRepository<Golo, Integer>{
    @Query("SELECT g FROM Golo g GROUP BY g.marcador, g ORDER BY count(g.tipo)")
    public List<Golo> findByGoals();
}
