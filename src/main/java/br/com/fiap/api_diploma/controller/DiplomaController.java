package br.com.fiap.api_diploma.controller;

import br.com.fiap.api_diploma.dto.DiplomaRequestDTO;
import br.com.fiap.api_diploma.dto.DiplomaTextDTO;
import br.com.fiap.api_diploma.model.Diploma;
import br.com.fiap.api_diploma.service.DiplomaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/diplomas")
public class DiplomaController {

    @Autowired
    private DiplomaService diplomaService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Diploma> create(@RequestBody @Valid DiplomaRequestDTO diplomaDTO) {
        return ResponseEntity.ok(diplomaService.create(diplomaDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getDiplomaText(@PathVariable UUID id) {
        Diploma diploma = diplomaService.findById(id);
        DiplomaTextDTO dto = new DiplomaTextDTO(diploma);
        return ResponseEntity.ok(dto.gerarTexto());
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Diploma>> getAll() {
        return ResponseEntity.ok(diplomaService.findAll());
    }
}
