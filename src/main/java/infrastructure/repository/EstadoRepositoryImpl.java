package infrastructure.repository;

import domain.model.Estado;
import domain.repository.EstadoRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class EstadoRepositoryImpl implements EstadoRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Estado> listar() {
        return manager.createQuery("from Estado", Estado.class).getResultList();
    }
    @Override
    @Transactional
    public Estado buscar(Long id) {
        return manager.find(Estado.class, id);
    }
    @Override
    @Transactional
    public Estado salvar(Estado estado) {
        return manager.merge(estado);
    }
    @Override
    @Transactional
    public void remover(Estado estado) {
        estado = buscar(estado.getId());
        manager.remove(estado);
    }
}
