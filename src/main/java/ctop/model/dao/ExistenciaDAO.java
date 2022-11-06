package ctop.model.dao;

import org.springframework.data.repository.CrudRepository;

import ctop.model.entity.Existencia;

public interface ExistenciaDAO extends CrudRepository<Existencia, Long> {
    
}
