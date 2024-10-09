package com.api.ejemplo.demo.controllers;

import com.api.ejemplo.demo.exepciones.ModelNotFoundException;
import com.api.ejemplo.demo.model.Fondo;
import com.api.ejemplo.demo.service.FondoService;

import org.apache.http.HttpStatus;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/fondos")
public class FondoController {

    private final FondoService fondoService;
    
    public FondoController(FondoService fondoService) {
        this.fondoService = fondoService;
    }

    @PostMapping
    public ResponseEntity<Fondo> crearFondo(@RequestBody Fondo fondo) {
        Fondo nuevoFondo = fondoService.crearFondo(fondo.getNombreFondo(), fondo.getDescripcion(), fondo.getMontoMinimo());;
        return ResponseEntity.ok(nuevoFondo);
    }

    @GetMapping("/{fondoId}")
    public ResponseEntity<Fondo> obtenerFondo(@PathVariable String fondoId) {
        Optional<Fondo> fondo = fondoService.obtenerFondo(fondoId);
        return fondo.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Fondo>> obtenerTodosLosFondos() {
        List<Fondo> fondos = fondoService.obtenerTodosLosFondos();
        return ResponseEntity.ok(fondos);
    }

    
    @GetMapping("/buscar/{nombreFondo}")
    public ResponseEntity<Fondo> buscarPorNombreFondo(@PathVariable String nombreFondo) {
        Fondo fondo = fondoService.buscarPorNombreFondo(nombreFondo);
        return ResponseEntity.ok(fondo);
    }

    @GetMapping("/validar-saldo")
    public ResponseEntity<String> validarSaldo(@RequestParam String clienteId, @RequestParam String fondoId) {
        String mensaje = fondoService.validarSaldo(clienteId, fondoId);
        return ResponseEntity.ok(mensaje);
    }
}
   