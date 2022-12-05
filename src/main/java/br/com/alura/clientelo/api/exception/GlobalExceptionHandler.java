package br.com.alura.clientelo.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		BindingResult result = e.getBindingResult();
		final List<FieldError> fieldErrors = result.getFieldErrors();
		ArgumentNotValid argumentNotValid = new ArgumentNotValid(fieldErrors.get(0).getField(),
				fieldErrors.get(0).getDefaultMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(argumentNotValid);
	}

	@ExceptionHandler(CategoriaNotFoundException.class)
	public ResponseEntity<Object> categoriaNaoEncontrada(CategoriaNotFoundException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Categoria não encontrada!");
	}

	@ExceptionHandler(ClienteNotFoundException.class)
	public ResponseEntity<Object> clienteNaoEncontrado(ClienteNotFoundException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cliente não encontrado!");
	}

	@ExceptionHandler(ProdutoNotFoundException.class)
	public ResponseEntity<Object> produtoNaoEncontrado(ProdutoNotFoundException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	}

	@ExceptionHandler(ProdutoSemEstoqueException.class)
	public ResponseEntity<Object> produtoSemEstoque(ProdutoSemEstoqueException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	}
}
