package br.com.alura.clientelo.dao;

import br.com.alura.clientelo.api.form.CategoriaForm;
import br.com.alura.clientelo.orm.Categoria;
import br.com.alura.clientelo.service.CrudCategoriaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CategoriaDaoTestes {
	private final CrudCategoriaService service;

	@Autowired
	public CategoriaDaoTestes(CrudCategoriaService service) {
		this.service = service;
	}

	@Test
	public void deveCadastrarEListarCategorias() {
		CategoriaForm alimento = new CategoriaForm("ALIMENTO");
		CategoriaForm tecnologia = new CategoriaForm("TECNOLOGIA");
		CategoriaForm livro = new CategoriaForm("LIVRO");

		service.cadastra(alimento);
		service.cadastra(tecnologia);
		service.cadastra(livro);

		List<Categoria> categorias = service.listaTodas();

		Assertions.assertEquals(1, categorias.stream().filter(categoria -> categoria.getNome()
				.equals(alimento.nome())).count());
		Assertions.assertEquals(1, categorias.stream().filter(categoria -> categoria.getNome()
				.equals(tecnologia.nome())).count());
		Assertions.assertEquals(1, categorias.stream().filter(categoria -> categoria.getNome()
				.equals(livro.nome())).count());
	}

	@Test
	public void deveAtualizarNomeCategoria() {
		CategoriaForm categoria = new CategoriaForm("pet");

		service.cadastra(categoria);

		CategoriaForm categoriaAtualizado = new CategoriaForm("PET", categoria.status());
		Long id = service.buscaPorNome(categoria.nome()).get().getId();

		service.atualizaCategoria(id, categoria);

		Assertions.assertEquals(categoriaAtualizado.nome(), service.buscaPorId(id).get().getNome());
	}

	@Test
	public void deveAtualizarStatusCategoria() {
//		Categoria categoria = new Categoria("PET");
//		Categoria buscaCategoriaPet = service.buscaPorNome(categoria.getNome());
//		if (buscaCategoriaPet == null) {
//			service.cadastra(categoria);
//		} else {
//			categoria = buscaCategoriaPet.get(0);
//		}
//		categoria.setStatus(StatusCategoria.INATIVA);
//		service.atualizaCategoria(categoria);
//
//		Categoria categoriaCadastrada = service.buscaPorId(categoria.getId()).get();
//		Assertions.assertEquals(categoria.getStatus().name(), categoriaCadastrada.getStatus().name());
	}



}
