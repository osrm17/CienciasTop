package ctop.model.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ctop.model.entity.Producto;

@Repository
public interface ProductoDAO extends CrudRepository<Producto, String> {

}
