package br.com.alura.clientelo.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		BindingResult result = e.getBindingResult();
		final List<FieldError> fieldErrors = result.getFieldErrors();
		ArgumentNotValidException argumentNotValidException = new ArgumentNotValidException(fieldErrors.get(0).getField(),
				fieldErrors.get(0).getDefaultMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(argumentNotValidException);
	}

}
