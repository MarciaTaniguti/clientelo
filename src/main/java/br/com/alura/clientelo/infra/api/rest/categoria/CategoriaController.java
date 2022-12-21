package br.com.alura.clientelo.infra.api.rest.categoria;

import br.com.alura.clientelo.api.form.CategoriaForm;
import br.com.alura.clientelo.core.entity.categoria.Categoria;
import br.com.alura.clientelo.core.usecase.categoria.CategoriaService;
import br.com.alura.clientelo.core.usecase.dto.RelatorioVendasPorCategoriaDTO;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@Api(value = "API para Categoria", produces = MediaType.APPLICATION_JSON_VALUE)
@RequestMapping(path = {"/api/categorias"}, produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoriaController {
	@Autowired
	private CategoriaService categoriaService;

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> cadastrar(@RequestBody @Valid CategoriaForm categoria) {
		Categoria categoriaCriada = categoriaService.cadastrar(categoria);
		String id = String.valueOf(categoriaCriada.getId());
		return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri()).body(categoriaCriada);
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<CategoriaForm> exibirDetalhes(@PathVariable Long id) {
		CategoriaForm categoria = categoriaService.buscaPorId(id);
		return ResponseEntity.ok().body(categoria);
	}

	@GetMapping(path = "/vendas")
	@Cacheable(value = "RelatorioVendasPorCategoria")
	public ResponseEntity<List<RelatorioVendasPorCategoriaDTO>> relatorioVendasPorCategoria() {
		return ResponseEntity.ok().body(categoriaService.relatorioVendasPorCategoria());
	}
}
