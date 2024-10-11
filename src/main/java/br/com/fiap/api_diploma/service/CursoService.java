package br.com.fiap.api_diploma.service;

import br.com.fiap.api_diploma.dto.CursoRequestDTO;
import br.com.fiap.api_diploma.exception.BusinessException;
import br.com.fiap.api_diploma.model.Curso;
import br.com.fiap.api_diploma.repository.DiplomaRepository;
import br.com.fiap.api_diploma.repository.DiplomadoRepository;
import br.com.fiap.api_diploma.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public Curso create(CursoRequestDTO dto) {
        validarCurso(dto);

        var curso = new Curso();
        curso.setNome(dto.nome());
        curso.setTipo(dto.tipo());
        return cursoRepository.save(curso);
    }

    private void validarCurso(CursoRequestDTO dto) {
        if (cursoRepository.existsByNome(dto.nome())) {
            throw new BusinessException("Já existe um curso com este nome");
        }
    }

    public List<Curso> findAll() {
        return cursoRepository.findAll();
    }
}