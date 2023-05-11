package br.com.letscode.teste.controller;

import br.com.letscode.teste.dto.ResultadoDTO;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class BaseController {

    //Tratamento dos erros
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultadoDTO handValidException(MethodArgumentNotValidException ex) {
            ResultadoDTO resultadoDTO = new ResultadoDTO();
            ex.getBindingResult().getAllErrors().forEach(e -> {
                String fieldName = ((FieldError)e).getField();
                String errorMessage = e.getDefaultMessage();
                resultadoDTO.putError(fieldName, errorMessage);
            });
            return resultadoDTO;
        }
    }

