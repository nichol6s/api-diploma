package br.com.fiap.api_diploma.service;

import br.com.fiap.api_diploma.dto.DiplomaRequestDTO;
import br.com.fiap.api_diploma.exception.BusinessException;
import br.com.fiap.api_diploma.exception.ResourceNotFoundException;
import br.com.fiap.api_diploma.model.Curso;
import br.com.fiap.api_diploma.model.Diploma;
import br.com.fiap.api_diploma.model.Diplomado;
import br.com.fiap.api_diploma.repository.DiplomaRepository;
import br.com.fiap.api_diploma.repository.DiplomadoRepository;
import br.com.fiap.api_diploma.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DiplomaService {

    @Autowired
    private DiplomaRepository diplomaRepository;

    @Autowired
    private DiplomadoRepository diplomadoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    public Diploma create(DiplomaRequestDTO dto) {
        var diplomado = diplomadoRepository.findById(dto.diplomadoId())
                .orElseThrow(() -> new ResourceNotFoundException("Diplomado não encontrado"));

        var curso = cursoRepository.findById(dto.cursoId())
                .orElseThrow(() -> new ResourceNotFoundException("Curso não encontrado"));

        validarDiploma(diplomado, curso);

        var diploma = new Diploma();
        diploma.setDiplomado(diplomado);
        diploma.setCurso(curso);
        diploma.setDataConclusao(dto.dataConclusao());
        diploma.setSexoReitor(dto.sexoReitor());
        diploma.setNomeReitor(dto.nomeReitor());

        return diplomaRepository.save(diploma);
    }

    public Diploma update(UUID id, DiplomaRequestDTO dto) {
        Diploma diploma = diplomaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Diploma não encontrado"));

        var diplomado = diplomadoRepository.findById(dto.diplomadoId())
                .orElseThrow(() -> new ResourceNotFoundException("Diplomado não encontrado"));

        var curso = cursoRepository.findById(dto.cursoId())
                .orElseThrow(() -> new ResourceNotFoundException("Curso não encontrado"));

        // Verifica se já existe outro diploma para o mesmo diplomado e curso
        if (!diploma.getDiplomado().equals(diplomado) || !diploma.getCurso().equals(curso)) {
            validarDiploma(diplomado, curso);
        }

        diploma.setDiplomado(diplomado);
        diploma.setCurso(curso);
        diploma.setDataConclusao(dto.dataConclusao());
        diploma.setSexoReitor(dto.sexoReitor());
        diploma.setNomeReitor(dto.nomeReitor());

        return diplomaRepository.save(diploma);
    }

    public void delete(UUID id) {
        Diploma diploma = diplomaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Diploma não encontrado"));

        diplomaRepository.delete(diploma);
    }

    private void validarDiploma(Diplomado diplomado, Curso curso) {
        var diplomaExistente = diplomaRepository.findByDiplomadoAndCurso(diplomado, curso);
        if (diplomaExistente.isPresent()) {
            throw new BusinessException("Já existe um diploma emitido para este diplomado neste curso");
        }
    }

    public Diploma findById(UUID id) {
        return diplomaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Diploma não encontrado"));
    }

    public List<Diploma> findAll() {
        return diplomaRepository.findAll();
    }
}