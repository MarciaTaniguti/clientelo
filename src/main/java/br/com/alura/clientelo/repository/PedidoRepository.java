package br.com.alura.clientelo.repository;

import br.com.alura.clientelo.orm.Pedido;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends CrudRepository<Pedido, Long> {
	@Query(value ="SELECT IFNULL((SELECT count(p.id) " +
					"FROM PEDIDO p " +
					"GROUP BY cliente_id " +
					"HAVING cliente_id = :idCliente),0);", nativeQuery = true)
	Long quantidadePedidoPorCliente(Long idCliente);
}
