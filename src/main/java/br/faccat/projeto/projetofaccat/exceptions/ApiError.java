/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.faccat.projeto.projetofaccat.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.http.HttpStatus;

/**
 *
 * @author tim
 */
public class ApiError {
    
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    
    private HttpStatus status;
    
    private String message;
    
    private List erros;

    public ApiError() {
    }

    public ApiError(LocalDateTime timestamp, HttpStatus status, String message, List erros) {
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
        this.erros = erros;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List getErros() {
        return erros;
    }

    public void setErros(List erros) {
        this.erros = erros;
    }
    
    
}
