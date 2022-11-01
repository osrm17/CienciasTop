package ctop.model.dao;

import org.springframework.data.repository.CrudRepository;

import ctop.model.entity.Producto;

public interface ProductoDAO extends CrudRepository<Producto, String> {

}
