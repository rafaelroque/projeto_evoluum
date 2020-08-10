package com.evoluum.api.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.evoluum.api.exception.ListagemException;


@ControllerAdvice
public class ListagemExceptionHandler {
	
	@ExceptionHandler(value = ListagemException.class)
	public ResponseEntity<String> customErrorPage(){
		
		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body("Erro processsamento "); 
	}

}
