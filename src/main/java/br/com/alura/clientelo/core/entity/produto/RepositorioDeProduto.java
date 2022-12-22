package br.com.alura.clientelo.core.entity.produto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Repository
public interface RepositorioDeProduto {
	Produto cadastrar(Produto produto);
	void atualizar(List<Produto> produtos);
	Optional<Produto> buscaPorId(Long id);
	List<Produto> buscaPorId(List<Long> ids);
	Page<Produto> buscaTodos(Pageable pageable);
	List<Produto> listaIndisponiveis();
	BigInteger temEstoque(Long id, Long qtdCompra);
}
