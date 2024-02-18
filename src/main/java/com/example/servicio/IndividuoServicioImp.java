package com.example.servicio;

import com.example.dao.IndividuoDao;
import com.example.domain.Individuo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class IndividuoServicioImp implements IndividuoServicio {
    
    @Autowired
    private IndividuoDao individuoDao;
    
    @Override
    @Transactional(readOnly = true)
    public List<Individuo> listaIndividuos() {
        return (List<Individuo>) individuoDao.findAll();
    }

    @Override
    @Transactional
    public void salvar(Individuo individuo) {
        individuoDao.save(individuo);
    }

    @Override
    @Transactional
    public void borrar(Individuo individuo) {
        individuoDao.delete(individuo);
    }
    @Override
    @Transactional(readOnly = true)
    public Individuo localizarIndividuo(Long id) {
        return individuoDao.findById(id).orElse(null);
    }
    @Override
    @Transactional
    public void editarIndividuo(Long id_individuo, Individuo individuo) {
    Individuo individuoExistente = localizarIndividuo(id_individuo);
    if (individuoExistente != null) {
        // Actualizar los datos del individuo existente con los datos del individuo recibido
        individuoExistente.setUsuario(individuo.getUsuario());
        individuoExistente.setCorreo(individuo.getCorreo());
        individuoExistente.setContrasena(individuo.getContrasena());
        // Guardar los cambios en la base de datos
        individuoDao.save(individuoExistente);
    }
}
}