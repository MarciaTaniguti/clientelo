package br.com.alura.clientelo.repository;

import br.com.alura.clientelo.core.entity.cliente.Endereco;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends CrudRepository<Endereco, Long> {

}
