package ctop.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ctop.model.dao.ExistenciaDAO;
import ctop.model.entity.Existencia;

/**
 * Clase que tiene la implementacion del servicio que manejara las existencias.
 * 
 * @version 1.0
 */
@Service
public class ExistenciaServiceImpl implements ServiceInterface<Existencia, Long> {

    @Autowired
    private ExistenciaDAO existenciaDAO;

    @Override
    @Transactional(readOnly = true)
    public Iterable<Existencia> findAll() {
        return existenciaDAO.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Existencia findById(Long id) {
        return existenciaDAO.findById(id).orElse(null);
    }

    @Override
    @Transactional()
    public Existencia save(Existencia existencia) {
        return existenciaDAO.save(existencia);
    }

    @Override
    @Transactional()
    public void delete(Long id) {
        existenciaDAO.deleteById(id);
    }
}
