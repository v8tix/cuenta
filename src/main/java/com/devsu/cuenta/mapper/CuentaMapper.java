package com.devsu.cliente.mapper;

import com.devsu.cliente.dto.ClienteRequest;
import com.devsu.cliente.model.Cliente;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {

    private final ModelMapper modelMapper;

    public ClienteMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Cliente convertToCliente(ClienteRequest clienteRequest) {
        return modelMapper.map(clienteRequest, Cliente.class);
    }

    public void updateClienteFromRequest(ClienteRequest clienteRequest, Cliente existingCliente) {
        modelMapper.map(clienteRequest, existingCliente);
    }
}
