package com.api.ejemplo.demo.controllers;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.api.ejemplo.demo.model.Cliente;
import com.api.ejemplo.demo.model.FondoSuscrito;
import com.api.ejemplo.demo.model.Transaccion;
import com.api.ejemplo.demo.service.ClienteService;


@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
	private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("/{id}")
    public Cliente obtenerCliente(@PathVariable String id) throws Exception {
        return clienteService.getClienteById(id);
    }

    @PostMapping
    public Cliente crearCliente(@RequestBody Cliente cliente) {
        return clienteService.crearCliente(cliente);
    }
	 
}
