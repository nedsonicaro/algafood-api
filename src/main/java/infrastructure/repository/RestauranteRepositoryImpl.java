package infrastructure.repository;


import domain.model.Restaurante;
import domain.repository.RestauranteRepository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

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
    public void remover(Restaurante restaurante) {
        restaurante = buscar(restaurante.getId());
        manager.remove(restaurante);
    }
}
