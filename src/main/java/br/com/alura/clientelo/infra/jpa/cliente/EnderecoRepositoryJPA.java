package br.com.alura.clientelo.infra.jpa.cliente;

import br.com.alura.clientelo.core.entity.cliente.Endereco;
import br.com.alura.clientelo.core.usecase.cliente.RepositorioDeEndereco;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepositoryJPA extends CrudRepository<Endereco, Long> {

}
