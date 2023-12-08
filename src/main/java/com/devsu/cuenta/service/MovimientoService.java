package com.devsu.cuenta.service;

import com.devsu.cuenta.dto.MovimientoRequest;
import com.devsu.cuenta.mapper.MovimientoMapper;
import com.devsu.cuenta.model.Movimiento;
import com.devsu.cuenta.repository.CuentaRepository;
import com.devsu.cuenta.repository.MovimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MovimientoService {

    private final MovimientoRepository movimientoRepository;
    private final MovimientoMapper movimientoMapper;
    private final CuentaRepository cuentaRepository;

    @Autowired
    public MovimientoService(MovimientoRepository movimientoRepository, MovimientoMapper movimientoMapper, CuentaRepository cuentaRepository) {
        this.movimientoRepository = movimientoRepository;
        this.movimientoMapper = movimientoMapper;
        this.cuentaRepository = cuentaRepository;
    }

    public List<Movimiento> getAllMovimientos() {
        return movimientoRepository.findAll();
    }

    public Optional<Movimiento> getMovimientoById(UUID id) {
        return movimientoRepository.findById(id);
    }

    public Optional<Movimiento> createMovimiento(MovimientoRequest movimientoRequest) {
        UUID cuentaId = movimientoRequest.getCuentaId();
        Optional<Movimiento> createdMovimiento = cuentaId != null ?
                cuentaRepository.findById(cuentaId)
                        .map(cuenta -> {
                            Movimiento movimiento = movimientoMapper.convertToMovimiento(movimientoRequest);
                            movimiento.setCuenta(cuenta);
                            return movimientoRepository.save(movimiento);
                        }) :
                Optional.empty();

        return createdMovimiento;
    }

    public Optional<Movimiento> updateMovimiento(UUID movimientoId, MovimientoRequest movimientoRequest) {
        Optional<Movimiento> existingMovimientoOptional = movimientoRepository.findById(movimientoId);

        return existingMovimientoOptional.map(existingMovimiento -> {
            movimientoMapper.updateMovimientoFromRequest(movimientoRequest, existingMovimiento);
            return Optional.of(movimientoRepository.save(existingMovimiento));
        }).orElse(Optional.empty());
    }

    public void deleteMovimiento(UUID id) {
        movimientoRepository.deleteById(id);
    }
}