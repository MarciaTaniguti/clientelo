package br.com.alura.clientelo.repository;

import br.com.alura.clientelo.orm.Categoria;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CategoriaRespository extends CrudRepository<Categoria, Long> {
	List<Categoria> findByNome(String nome);

}
