/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.faccat.projeto.projetofaccat.exceptions;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 *
 * @author tim
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    
 
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<?> handleConstraintViolationException(Exception ex, WebRequest request) {
		
		List<String> details = new ArrayList<String>();
		details.add(ex.getMessage());
		
		ApiError err = new ApiError(LocalDateTime.now(),HttpStatus.BAD_REQUEST, "Constraint Violation" ,details);
		
		return ResponseEntityBuilder.build(err);
	}
	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<?> handleBadCretendial(Exception ex, WebRequest request) {
		List<String> details = new ArrayList<String>();
		details.add(ex.getMessage());
		ApiError err = new ApiError(LocalDateTime.now(),HttpStatus.UNAUTHORIZED, "Login ou senha incorreto" ,details);
		return ResponseEntityBuilder.build(err);
	}
        @ExceptionHandler(InvalidTokenException.class)
	public ResponseEntity<?> InvalidTokenException(Exception ex, WebRequest request) {
		List<String> details = new ArrayList<String>();
		details.add(ex.getMessage());
		ApiError err = new ApiError(LocalDateTime.now(),HttpStatus.UNAUTHORIZED, "Token Inválido" ,details);
		return ResponseEntityBuilder.build(err);
	}
        @ExceptionHandler(TokenExpiredException.class)
	public ResponseEntity<?> TokenExpiredException(Exception ex, WebRequest request) {
		List<String> details = new ArrayList<String>();
		details.add(ex.getMessage());
		ApiError err = new ApiError(LocalDateTime.now(),HttpStatus.valueOf(419), "Token Expirado" ,details);
		return ResponseEntityBuilder.build(err);
	}
        @ExceptionHandler(DataNotFoundException.class)
	public ResponseEntity<?> dataNotFound(Exception ex, WebRequest request) {
		List<String> details = new ArrayList<String>();
		details.add(ex.getMessage());
		ApiError err = new ApiError(LocalDateTime.now(),HttpStatus.NOT_FOUND, "Dado não encontrado" ,details);
		return ResponseEntityBuilder.build(err);
	}
}
