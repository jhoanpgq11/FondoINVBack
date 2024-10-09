package com.api.ejemplo.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HelloController {

	
	@GetMapping("/saludo")
    public String saludar() {
        return "¡HelloFondo!!";
    }

    @PostMapping("/crear")
    public String crear(@RequestBody String data) {
        // Lógica para crear algo
        return "Creado: " + data;
    }

}
