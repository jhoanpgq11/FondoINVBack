package com.api.ejemplo.demo.service;


import com.api.ejemplo.demo.model.Cliente;
import com.api.ejemplo.demo.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    // Obtener cliente por ID
    public Cliente getClienteById(String clienteId) throws Exception {
        Optional<Cliente> cliente = clienteRepository.findById(clienteId);
        if (cliente.isPresent()) {
            return cliente.get();
        } else {
            throw new Exception("ID Cliente no encontrado: " + clienteId);
        }
    }

    // Crear un nuevo cliente
    public Cliente crearCliente(Cliente cliente) {
        // Puedes agregar lógica adicional aquí, como validaciones específicas antes de crear el cliente
        return clienteRepository.save(cliente);
    }

    // Actualizar un cliente existente
    public Cliente actualizarCliente(String clienteId, Cliente clienteActualizado) throws Exception {
        Cliente clienteExistente = getClienteById(clienteId);

        clienteExistente.setNombres(clienteActualizado.getNombres());
        clienteExistente.setEmail(clienteActualizado.getEmail());
        clienteExistente.setTelefono(clienteActualizado.getTelefono());
        clienteExistente.setSaldoActual(clienteActualizado.getSaldoActual());
        // Actualizar otros campos según sea necesario

        return clienteRepository.save(clienteExistente);
    }

    // Obtener todos los clientes
    public List<Cliente> obtenerTodosLosClientes() {
        return (List<Cliente>) clienteRepository.findAll();
    }

    // Eliminar un cliente
    public void eliminarCliente(String clienteId) throws Exception {
        Cliente cliente = getClienteById(clienteId);
        clienteRepository.delete(cliente);
    }

    // Lógica adicional relacionada con la suscripción y cancelación de fondos
    public String validarSaldoParaFondo(Cliente cliente, double montoRequerido) {
        if (cliente.getSaldoActual() >= montoRequerido) {
            return "Saldo suficiente";
        } else {
            return "No tiene saldo disponible";
        }
    }

    public void actualizarSaldoCliente(Cliente cliente, double monto) {
        cliente.setSaldoActual(cliente.getSaldoActual() - monto);  
        clienteRepository.save(cliente);
    }
}
