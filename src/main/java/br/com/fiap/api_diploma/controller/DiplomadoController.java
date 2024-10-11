package br.com.fiap.api_diploma.controller;

import br.com.fiap.api_diploma.dto.DiplomadoRequestDTO;
import br.com.fiap.api_diploma.model.Diplomado;
import br.com.fiap.api_diploma.service.DiplomadoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/diplomados")
@PreAuthorize("hasRole('ADMIN')")
public class DiplomadoController {

    @Autowired
    private DiplomadoService diplomadoService;

    @PostMapping
    public ResponseEntity<Diplomado> create(@RequestBody @Valid DiplomadoRequestDTO diplomadoDTO) {
        return ResponseEntity.ok(diplomadoService.create(diplomadoDTO));
    }

    @GetMapping
    public ResponseEntity<List<Diplomado>> getAll() {
        return ResponseEntity.ok(diplomadoService.findAll());
    }
}