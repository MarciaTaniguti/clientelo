package br.com.alura.clientelo.core.entity.categoria;

import br.com.alura.clientelo.core.usecase.dto.RelatorioVendasPorCategoriaDTO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RepositorioDeCategoria {
	Optional<Categoria> buscarPorId(Long id);
	Optional<Categoria> buscarPorNome(String nome);
	Categoria cadastrar(Categoria categoria);
	Categoria atualizar(Categoria categoria);
	List<Categoria> listarTodas();
	List<RelatorioVendasPorCategoriaDTO> relatorioVendasPorCategoria();

}
