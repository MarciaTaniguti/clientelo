package br.com.alura.clientelo.infra.jpa.cliente;

import br.com.alura.clientelo.core.entity.cliente.Cliente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepositoryJPA extends CrudRepository<Cliente, Long> {
	Optional<Cliente> findByNome(String nome);
	Optional<Cliente> findByCpf(String cpf);
}
