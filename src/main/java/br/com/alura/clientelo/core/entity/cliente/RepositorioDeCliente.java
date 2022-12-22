package br.com.alura.clientelo.core.entity.cliente;

import br.com.alura.clientelo.core.entity.cliente.Cliente;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface RepositorioDeCliente {
	Optional<Cliente> buscarPorId(Long id);
	Cliente cadastrar(Cliente cliente);
	Optional<Cliente> atualizar(Cliente cliente);
	Optional<Cliente> buscarPorNome(String nome);
	Optional<Cliente> buscarPorCpf(String documento);
	void remover(Cliente cliente);
	List<Cliente> listaTodos();
}
