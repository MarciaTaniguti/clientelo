package br.com.alura.clientelo.repository;

import br.com.alura.clientelo.orm.ItemPedido;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemPedidoRepository extends CrudRepository<ItemPedido, Long> {
}
