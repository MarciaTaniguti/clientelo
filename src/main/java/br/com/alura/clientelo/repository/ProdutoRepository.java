package br.com.alura.clientelo.repository;

import br.com.alura.clientelo.orm.Produto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends CrudRepository<Produto, Long> {
	@Query("SELECT p FROM Produto p WHERE p.quantidadeEstoque = 0")
	List<Produto> listaIndisponiveis();
}
