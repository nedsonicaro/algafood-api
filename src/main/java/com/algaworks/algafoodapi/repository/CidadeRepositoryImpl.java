package com.algaworks.algafoodapi.repository;

import com.algaworks.algafoodapi.domain.model.Cidade;
import com.algaworks.algafoodapi.domain.repository.CidadeRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class CidadeRepositoryImpl implements CidadeRepository {

    @PersistenceContext
    private EntityManager manager;
    @Override
    @Transactional
    public List<Cidade> listar() {
       return manager.createQuery("from Cidade", Cidade.class).getResultList();
    }
    @Override
    @Transactional
    public Cidade buscar(Long id) {
        return manager.find(Cidade.class, id);
    }
    @Override
    @Transactional
    public Cidade salvar(Cidade cidade) {
        return manager.merge(cidade);
    }
    @Override
    @Transactional
    public void remover(Long id) {
        Cidade cidade = buscar(id);
        if (cidade == null) {
            throw new EmptyResultDataAccessException(1);
        }
        manager.remove(cidade);
    }
}

