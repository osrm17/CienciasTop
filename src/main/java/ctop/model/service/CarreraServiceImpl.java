package ctop.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ctop.model.dao.CarreraDAO;
import ctop.model.entity.Carrera;

/**
 * Clase que tiene la implementacion del servicio que manejara las carreras.
 * 
 * @version 1.0
 */
@Service
public class CarreraServiceImpl implements CarreraServiceInterface {

    @Autowired
    private CarreraDAO carreraDAO;

    @Override
    @Transactional(readOnly = true)
    public Iterable<Carrera> findAll() {
        return carreraDAO.findAll();
    }
}
