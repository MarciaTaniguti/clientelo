package br.com.alura.clientelo.dao;

import br.com.alura.clientelo.orm.Categoria;
import br.com.alura.clientelo.orm.StatusCategoria;
import br.com.alura.clientelo.service.CrudCategoriaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CategoriaDaoTestes {
	private final CrudCategoriaService service;

	public CategoriaDaoTestes(CrudCategoriaService service) {
		this.service = service;
	}

	@Test
	public void deveCadastrarEListarCategorias() {
		Categoria alimento = new Categoria("ALIMENTO");
		Categoria tecnologia = new Categoria("TECNOLOGIA");
		Categoria livro = new Categoria("LIVRO");

		service.cadastra(alimento);
		service.cadastra(tecnologia);
		service.cadastra(livro);

		List<Categoria> categorias = service.listaTodas();

		Assertions.assertEquals(1, categorias.stream().filter(categoria -> categoria.getNome()
				.equals(alimento.getNome())).count());
		Assertions.assertEquals(1, categorias.stream().filter(categoria -> categoria.getNome()
				.equals(tecnologia.getNome())).count());
		Assertions.assertEquals(1, categorias.stream().filter(categoria -> categoria.getNome()
				.equals(livro.getNome())).count());
	}

	@Test
	public void deveAtualizarNomeCategoria() {
		Categoria categoria = new Categoria("pet");

		service.cadastra(categoria);

		categoria.setNome("PET");
		service.atualizaCategoria(categoria);

		Assertions.assertEquals(categoria.getNome(), service.buscaPorId(categoria.getId()).get().getNome());
	}

	@Test
	public void deveAtualizarStatusCategoria() {
		Categoria categoria = new Categoria("PET");
		List<Categoria> buscaCategoriaPet = service.buscaPorNome(categoria.getNome());
		if (buscaCategoriaPet.isEmpty()) {
			service.cadastra(categoria);
		} else {
			categoria = buscaCategoriaPet.get(0);
		}
		categoria.setStatus(StatusCategoria.INATIVA);
		service.atualizaCategoria(categoria);

		Categoria categoriaCadastrada = service.buscaPorId(categoria.getId()).get();
		Assertions.assertEquals(categoria.getStatus().name(), categoriaCadastrada.getStatus().name());
	}



}
