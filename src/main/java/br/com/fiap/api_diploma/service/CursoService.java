package br.com.fiap.api_diploma.service;

import br.com.fiap.api_diploma.dto.CursoRequestDTO;
import br.com.fiap.api_diploma.exception.BusinessException;
import br.com.fiap.api_diploma.exception.ResourceNotFoundException;
import br.com.fiap.api_diploma.model.Curso;
import br.com.fiap.api_diploma.repository.DiplomaRepository;
import br.com.fiap.api_diploma.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private DiplomaRepository diplomaRepository;

    public Curso create(CursoRequestDTO dto) {
        validarCurso(dto);

        var curso = new Curso();
        curso.setNome(dto.nome());
        curso.setTipo(dto.tipo());
        return cursoRepository.save(curso);
    }

    public Curso update(Long id, CursoRequestDTO dto) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Curso não encontrado"));

        // Verifica se já existe outro curso com o mesmo nome
        if (!curso.getNome().equals(dto.nome()) && cursoRepository.existsByNome(dto.nome())) {
            throw new BusinessException("Já existe um curso com este nome");
        }

        curso.setNome(dto.nome());
        curso.setTipo(dto.tipo());
        return cursoRepository.save(curso);
    }

    public void delete(Long id) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Curso não encontrado"));

        // Verifica se existem diplomas vinculados
        if (diplomaRepository.existsByCurso(curso)) {
            throw new BusinessException("Não é possível excluir um curso que possui diplomas vinculados");
        }

        cursoRepository.delete(curso);
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