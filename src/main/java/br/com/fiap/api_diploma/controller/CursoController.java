package br.com.fiap.api_diploma.controller;

import br.com.fiap.api_diploma.dto.CursoRequestDTO;
import br.com.fiap.api_diploma.model.Curso;
import br.com.fiap.api_diploma.service.CursoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cursos")
@PreAuthorize("hasRole('ADMIN')")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @PostMapping
    public ResponseEntity<Curso> create(@RequestBody @Valid CursoRequestDTO cursoDTO) {
        return ResponseEntity.ok(cursoService.create(cursoDTO));
    }

    @GetMapping
    public ResponseEntity<List<Curso>> getAll() {
        return ResponseEntity.ok(cursoService.findAll());
    }
}