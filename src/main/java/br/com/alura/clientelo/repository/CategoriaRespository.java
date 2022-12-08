package br.com.alura.clientelo.repository;

import br.com.alura.clientelo.dto.RelatorioVendasPorCategoriaDTO;
import br.com.alura.clientelo.orm.Categoria;
import br.com.alura.clientelo.relatorio.Relatorio;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CategoriaRespository extends CrudRepository<Categoria, Long> {
	List<Categoria> findByNome(String nome);

	@Query("SELECT new br.com.alura.clientelo.dto.RelatorioVendasPorCategoriaDTO(c.nome, COALESCE(SUM(ip.quantidade),0), COALESCE(sum(ip.valorPago),0)) " +
			"FROM br.com.alura.clientelo.orm.ItemPedido ip " +
			"JOIN ip.produto p " +
			"RIGHT JOIN p.categoria c " +
			"GROUP BY c.id")
	List<RelatorioVendasPorCategoriaDTO> relatorioVendasPorCategoria();

}
