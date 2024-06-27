package com.example.demo.controller;


import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "line-evaluation",
        produces = MediaType.APPLICATION_JSON_VALUE)
public class ControllerTest {


    @GetMapping(value = "")
    public ResponseEntity<?> getLineEvaluation() {
        return ResponseEntity.ok("TODO BIEN JARED SOLO TE TOCA REZAR");
    }


}
