package br.com.alura.clientelo.repository;

import br.com.alura.clientelo.orm.Categoria;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends CrudRepository<Categoria, Long> {

}
