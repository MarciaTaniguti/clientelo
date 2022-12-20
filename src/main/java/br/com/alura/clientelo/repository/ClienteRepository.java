package br.com.alura.clientelo.repository;

import br.com.alura.clientelo.core.entity.cliente.Cliente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long> {
	List<Cliente> findByNome(String nome);
}
