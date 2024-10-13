package br.com.fiap.api_diploma.service;

import br.com.fiap.api_diploma.dto.DiplomadoRequestDTO;
import br.com.fiap.api_diploma.exception.BusinessException;
import br.com.fiap.api_diploma.exception.ResourceNotFoundException;
import br.com.fiap.api_diploma.model.Diplomado;
import br.com.fiap.api_diploma.repository.DiplomaRepository;
import br.com.fiap.api_diploma.repository.DiplomadoRepository;
import br.com.fiap.api_diploma.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiplomadoService {

    @Autowired
    private DiplomadoRepository diplomadoRepository;

    @Autowired
    private DiplomaRepository diplomaRepository;

    public Diplomado create(DiplomadoRequestDTO dto) {
        validarDiplomado(dto);

        var diplomado = new Diplomado();
        diplomado.setNome(dto.nome());
        diplomado.setNacionalidade(dto.nacionalidade());
        diplomado.setNaturalidade(dto.naturalidade());
        diplomado.setRg(dto.rg());
        return diplomadoRepository.save(diplomado);
    }

    public Diplomado update(Long id, DiplomadoRequestDTO dto) {
        Diplomado diplomado = diplomadoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Diplomado não encontrado"));

        // Verifica se já existe outro diplomado com o mesmo RG
        if (!diplomado.getRg().equals(dto.rg()) && diplomadoRepository.existsByRg(dto.rg())) {
            throw new BusinessException("Já existe um diplomado com este RG");
        }

        diplomado.setNome(dto.nome());
        diplomado.setNacionalidade(dto.nacionalidade());
        diplomado.setNaturalidade(dto.naturalidade());
        diplomado.setRg(dto.rg());
        return diplomadoRepository.save(diplomado);
    }

    public void delete(Long id) {
        Diplomado diplomado = diplomadoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Diplomado não encontrado"));

        // Verifica se existem diplomas vinculados
        if (diplomaRepository.existsByDiplomado(diplomado)) {
            throw new BusinessException("Não é possível excluir um diplomado que possui diplomas vinculados");
        }

        diplomadoRepository.delete(diplomado);
    }

    private void validarDiplomado(DiplomadoRequestDTO dto) {
        if (diplomadoRepository.existsByRg(dto.rg())) {
            throw new BusinessException("Já existe um diplomado com este RG");
        }
    }

    public List<Diplomado> findAll() {
        return diplomadoRepository.findAll();
    }
}
