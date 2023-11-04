package com.kaique.ifood.exceptionHandler;

import java.time.LocalDateTime;

import org.springframework.beans.factory.parsing.Problem;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.kaique.ifood.exception.EntidadeEmUsoException;
import com.kaique.ifood.exception.EntidadeNaoEncontradaException;
import com.kaique.ifood.exception.NegocioException;

@ControllerAdvice
public class ApiExceptionHandler {

	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<?> trataNegocioException(NegocioException e) {
		ApiErro erro = ApiErro.builder().dataHora(LocalDateTime.now()).mensagem(e.getMessage()).build();

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}

	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<?> trataEntidadeNaoEncontradaException(EntidadeNaoEncontradaException e) {
		ApiErro erro = ApiErro.builder().dataHora(LocalDateTime.now()).mensagem(e.getMessage()).build();

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}

	@ExceptionHandler(EntidadeEmUsoException.class)
	public ResponseEntity<?> trataEntidadeEmUsoException(EntidadeEmUsoException e) {
		ApiErro erro = ApiErro.builder().dataHora(LocalDateTime.now()).mensagem(e.getMessage()).build();

		return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
	}
}
