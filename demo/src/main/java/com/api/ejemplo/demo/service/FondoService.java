package com.api.ejemplo.demo.service;

import com.api.ejemplo.demo.exepciones.ModelNotFoundException;
import com.api.ejemplo.demo.model.Cliente;
import com.api.ejemplo.demo.model.Fondo;
import com.api.ejemplo.demo.model.FondoSuscrito;
import com.api.ejemplo.demo.model.Transaccion;
import com.api.ejemplo.demo.repository.ClienteRepository;
import com.api.ejemplo.demo.repository.FondoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.api.ejemplo.demo.exepciones.ModelNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FondoService {

   
    private final FondoRepository fondoRepository;
    private final NotificationService notificationService;



    @Autowired
    private ClienteRepository clienteRepository;


    @Autowired
    public FondoService(FondoRepository fondoRepository) {
        this.fondoRepository = fondoRepository;
		this.notificationService = new NotificationService();
    }

     public String validarSaldo(String clienteId, String fondoId) {
        Cliente cliente = clienteRepository.findById(clienteId)
            .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        Fondo fondo = fondoRepository.findById(fondoId)
        .orElseThrow(() -> new RuntimeException("Fondo no encontrado"));

        if (cliente.getSaldoActual() < fondo.getMontoMinimo()) {
            return "No tiene saldo disponible " + fondo.getNombreFondo();
        }

        return "Puede vincularse " + fondo.getNombreFondo();
    }


    public Fondo crearFondo(String nombreFondo, String descripcion, double montoMinimo) {
        Fondo fondo = new Fondo();
        fondo.setNombreFondo(nombreFondo);
        fondo.setDescripcion(descripcion);
        fondo.setMontoMinimo(montoMinimo);

        return fondoRepository.save(fondo);
    }

    
    public Optional<Fondo> obtenerFondo(String fondoId) {
        return fondoRepository.findById(fondoId);
    }

    
    public List<Fondo> obtenerTodosLosFondos() {
        return (List<Fondo>) fondoRepository.findAll();
    }

   
    public Fondo buscarPorNombreFondo(String nombreFondo) {
        return fondoRepository.findByNombreFondo(nombreFondo).orElse(null);
    }
}