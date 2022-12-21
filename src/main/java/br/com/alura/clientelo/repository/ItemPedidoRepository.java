package br.com.alura.clientelo.repository;

import br.com.alura.clientelo.core.entity.pedido.item.ItemPedido;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemPedidoRepository extends CrudRepository<ItemPedido, Long> {
}
