package com.Ban.Mon_Stage.exception;

import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNullApi;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice // hadel exception globally
public class GlobalExceptionClass extends ResponseEntityExceptionHandler  {
    //ResponseEntityExceptionHandler fiha des methods li kaygeriou had l'exception
    // b status code diaoulhoum , response body diaoulhom

    @Override

    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status,  WebRequest request) {

        //WebRequest request:  to represent a request made by a user's web browser to a web server
        //MethodArgumentNotValidException ex: katpresenti dik la requete li deklanchate
        // ResponseEntity<Object>: tarika mzyana biha kanpersonalisiou la reponse dialna , status code, header, body dialna
        //**************************************************************************************************//
        // return super.handleMethodArgumentNotValid(ex, headers, status, request); hna katreturni lexception li mdifinya deja
        Map<String,Object> body=new LinkedHashMap<>();

        body.put("timestamp",System.currentTimeMillis());
        body.put("status",status.value());//  It returns the numeric representation of the HTTP status code
        List<String> errors= ex.getBindingResult()
                // ex : instance dial exception et  kola exception fiha getBindingResult
                // kaycollicti lik les errors li 3andek f validation f any field
                .getFieldErrors() // listi la list des errors li tcollictaou mn BindingResult
                // List<FieldError>
                //FieldError : validation error occurs , spring kaykriyi lik had obj et kaypresenti lik les errors
                .stream()
                .map(x->x.getField()+":"+x.getDefaultMessage())
                .collect(Collectors.toList());
        body.put("errors",errors);
        body.put("request",request.getContextPath());

        // json m3rafch kifach radi serializi one had l'objet
        // transient ma5aouch itserializa
        return new ResponseEntity<>(body,status);
        // ResponseEntity daroury tdir status

    }
    /*
     what is the difference between LinkedHashMap and HashMap ?
     LinkedHashMap: kayn l'ordre , kola element 3ando l'adresse dial le prochain element
     HashMap:makaynch l'ordre ,

    */
}
