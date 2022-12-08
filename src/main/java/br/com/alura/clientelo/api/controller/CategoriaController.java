package br.com.alura.clientelo.api.controller;

import br.com.alura.clientelo.api.form.CategoriaForm;
import br.com.alura.clientelo.dto.RelatorioVendasPorCategoriaDTO;
import br.com.alura.clientelo.service.CrudCategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = {"/api/categorias"}, produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoriaController {
	@Autowired
	private CrudCategoriaService categoriaService;

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CategoriaForm> cadastrar(@RequestBody @Valid CategoriaForm categoria) {
		CategoriaForm categoriaCriada = categoriaService.cadastra(categoria);
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaCriada);
	}

	@GetMapping(path = "/vendas")
	@Cacheable(value = "RelatorioVendasPorCategoria")
	public ResponseEntity<List<RelatorioVendasPorCategoriaDTO>> relatorioVendasPorCategoria() {
		return ResponseEntity.ok().body(categoriaService.relatorioVendasPorCategoria());
	}
}
