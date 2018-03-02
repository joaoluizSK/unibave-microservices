package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/properties")
public class PropertiesController {

    private String ola;
    private String tchau;

    public PropertiesController(@Value("${unibave.ola}") String ola,
                                @Value("${unibave.tchau}") String tchau) {
        this.ola = ola;
        this.tchau = tchau;
    }

    @GetMapping(path = "/ola")
    public ResponseEntity<String> getPropertiesOla() {
        return ResponseEntity.ok(ola);
    }

    @GetMapping(path = "/tchau")
    public ResponseEntity<String> getPropertiesTchau() {
        return ResponseEntity.ok(tchau);
    }

}
