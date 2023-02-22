package domain.repository;

import domain.model.Permissao;
import java.util.List;

public interface PermissaoRepository {
    List<Permissao> listar();
    Permissao buscar(Long id);
    Permissao salvar(Permissao permissao);
    public void remover(Permissao permissao);
}
