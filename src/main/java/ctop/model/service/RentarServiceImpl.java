package ctop.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ctop.model.dao.RentarDAO;
import ctop.model.dao.ExistenciaDAO;
import ctop.model.entity.Rentar;
import ctop.model.entity.Existencia;

/**
 * Clase que tiene la implementacion del servicio que manejara las rentas.
 * 
 * @version 1.0
 */
@Service
public class RentarServiceImpl implements ServiceInterface<Rentar, Long> {

    @Autowired
    private RentarDAO rentarDAO;

    @Autowired
    private ExistenciaDAO existenciaDAO;

    @Override
    @Transactional(readOnly = true)
    public Iterable<Rentar> findAll() {
        return rentarDAO.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Rentar findById(Long id) {
        return rentarDAO.findById(id).orElse(null);
    }

    @Override
    @Transactional()
    public Rentar save(Rentar renta) {
        return rentarDAO.save(renta);
    }

    @Override
    @Transactional()
    public void delete(Long id) {
        rentarDAO.deleteById(id);
    }

    public List<Existencia> getExistencias() {
        return (List<Existencia>) existenciaDAO.getExistencias();
    }

}
