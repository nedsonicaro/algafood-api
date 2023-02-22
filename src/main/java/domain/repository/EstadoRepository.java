package domain.repository;

import domain.model.Cozinha;
import domain.model.Estado;

import java.util.List;

public interface EstadoRepository {
    List<Estado> listar();
    Estado buscar(Long id);
    Estado salvar(Estado estado);
    public void remover(Estado estado);
}
