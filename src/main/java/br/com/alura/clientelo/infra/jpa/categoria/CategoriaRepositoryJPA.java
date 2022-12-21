package br.com.alura.clientelo.infra.jpa.categoria;

import br.com.alura.clientelo.core.entity.categoria.Categoria;
import br.com.alura.clientelo.core.usecase.dto.RelatorioVendasPorCategoriaDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoriaRepositoryJPA extends CrudRepository<Categoria, Long> {
	Optional<Categoria> findByNome(String nome);

	@Query("SELECT new br.com.alura.clientelo.core.usecase.dto.RelatorioProdutosMaisVendidosDTO(c.nome, COALESCE(SUM(ip.quantidade),0), COALESCE(sum(ip.valorPago),0)) " +
			"FROM br.com.alura.clientelo.core.entity.pedido.item.ItemPedido ip " +
			"JOIN ip.produto p " +
			"RIGHT JOIN p.categoria c " +
			"GROUP BY c.id")
	List<RelatorioVendasPorCategoriaDTO> relatorioVendasPorCategoria();
}
