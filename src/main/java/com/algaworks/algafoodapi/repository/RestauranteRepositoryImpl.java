package com.algaworks.algafoodapi.repository;


import com.algaworks.algafoodapi.domain.model.Restaurante;
import com.algaworks.algafoodapi.domain.repository.RestauranteRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class RestauranteRepositoryImpl implements RestauranteRepository {

    @PersistenceContext
    private EntityManager manager;
    @Override
    public List<Restaurante> listar() {
        return manager.createQuery("from Restaurante ", Restaurante.class).getResultList();
    }
    @Override
    @Transactional
    public Restaurante salvar(Restaurante restaurante) { return manager.merge(restaurante);
    }
    @Override
    @Transactional
    public Restaurante buscar(Long id) {
        return manager.find(Restaurante.class, id);
    }
    @Override
    @Transactional
    public void remover(Long id) {
        Restaurante restaurante = buscar(id);
        if (restaurante == null) {
            throw new EmptyResultDataAccessException(1);
        }
        manager.remove(restaurante);
    }
}
