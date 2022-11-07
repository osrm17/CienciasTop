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
public class ExistenciaServiceImpl implements ExistenciaServiceInterface {

    @Autowired
    private ExistenciaDAO existenciaDAO;

    @Override
    @Transactional(readOnly = true)
    public Iterable<Existencia> findAll() {
        return existenciaDAO.findAll();
    }
}
