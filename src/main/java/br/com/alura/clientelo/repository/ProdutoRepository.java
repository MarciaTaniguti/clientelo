package br.com.alura.clientelo.repository;

import br.com.alura.clientelo.core.entity.produto.Produto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface ProdutoRepository extends PagingAndSortingRepository<Produto, Long> {
	@Query("SELECT p FROM Produto p WHERE p.quantidadeEstoque = 0")
	List<Produto> listaIndisponiveis();

	@Query(value = "SELECT COUNT(id) FROM PRODUTO p where p.id in :ids", nativeQuery = true)
	Long qtdIdsEncontrados(List<Long> ids);

	@Query(value = "SELECT quantidade_estoque >= :qtdCompra FROM PRODUTO p WHERE id = :id", nativeQuery = true)
	BigInteger temEstoque(Long id, Long qtdCompra);  //TODO - tentar implementar com o Spring Specification
}
