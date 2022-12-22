package br.com.alura.clientelo.core.entity.cliente;

import br.com.alura.clientelo.core.entity.cliente.Endereco;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioDeEndereco {
	Endereco cadastrar(Endereco endereco);
}
