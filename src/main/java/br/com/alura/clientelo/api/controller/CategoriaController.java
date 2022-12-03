package br.com.alura.clientelo.api.controller;

import br.com.alura.clientelo.api.form.CategoriaForm;
import br.com.alura.clientelo.service.CrudCategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
}
