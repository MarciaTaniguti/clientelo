package br.com.alura.clientelo.core.entity.pedido;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RepositorioDePedido {
	Pedido cadastrar(Pedido pedido);
	Optional<Pedido> buscarDetalhePedido(Long id);
	List<Pedido> buscarTodos(List<Long> ids);
	Long qtdPedidoPorCliente(Long idCliente);
	Optional<Pedido> buscarPorId(Long id);
	List<Pedido> listaTodos();

}
