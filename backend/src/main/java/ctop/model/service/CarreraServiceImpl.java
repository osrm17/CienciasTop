package ctop.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ctop.model.dao.CarreraDAO;
import ctop.model.entity.Carrera;
import ctop.model.entity.CarreraId;

/**
 * Clase que tiene la implementacion del servicio que manejara las carreras.
 * 
 * @version 1.0
 */
@Service
public class CarreraServiceImpl implements ServiceInterface<Carrera, CarreraId> {

    @Autowired
    private CarreraDAO carreraDAO;

    @Override
    @Transactional(readOnly = true)
    public Iterable<Carrera> findAll() {
        return carreraDAO.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Carrera findById(CarreraId id) {
        return carreraDAO.findById(id).orElse(null);
    }

    @Override
    @Transactional()
    public Carrera save(Carrera carrera) {
        return carreraDAO.save(carrera);
    }

    @Override
    @Transactional()
    public void delete(CarreraId id) {
        carreraDAO.deleteById(id);
    }
}
