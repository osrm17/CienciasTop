package ctop.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ctop.model.dao.RentarDAO;
import ctop.model.entity.Rentar;

/**
 * Clase que tiene la implementacion del servicio que manejara las rentas.
 * 
 * @version 1.0
 */
@Service
public class RentarServiceImpl implements RentarServiceInterface {

    @Autowired
    private RentarDAO rentarDAO;

    @Override
    @Transactional(readOnly = true)
    public Iterable<Rentar> findAll() {
        return rentarDAO.findAll();
    }
}
