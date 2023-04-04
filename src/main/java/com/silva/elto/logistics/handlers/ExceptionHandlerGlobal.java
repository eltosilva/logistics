package com.silva.elto.logistics.handlers;

import com.silva.elto.logistics.dto.ErroDto;
import feign.FeignException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.NoSuchElementException;

@ControllerAdvice
public class ExceptionHandlerGlobal {

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<ErroDto> badRequestException(FeignException exception, WebRequest request){
        ErroDto erroDto = new ErroDto("CEP no formato inválido.", request.getDescription(false));
        return ResponseEntity.badRequest().body(erroDto);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroDto> badRequestException(MethodArgumentNotValidException exception, WebRequest request){

        ErroDto erroDto = new ErroDto(exception.getStackTrace().toString(), "");
        return ResponseEntity.badRequest().body(erroDto);
    }

    @ExceptionHandler({NoSuchElementException.class})
    public ResponseEntity<ErroDto> notFoundException(RuntimeException exception, WebRequest request){
        return ResponseEntity.notFound().build();
    }
}
