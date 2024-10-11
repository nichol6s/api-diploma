package br.com.fiap.api_diploma.repository;

import br.com.fiap.api_diploma.model.Curso;
import br.com.fiap.api_diploma.model.Diploma;
import br.com.fiap.api_diploma.model.Diplomado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface DiplomaRepository extends JpaRepository<Diploma, UUID> {
    Optional<Diploma> findByDiplomadoAndCurso(Diplomado diplomado, Curso curso);
}