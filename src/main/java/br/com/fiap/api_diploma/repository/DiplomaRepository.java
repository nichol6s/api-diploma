package br.com.fiap.api_diploma.repository;

import br.com.fiap.api_diploma.model.Curso;
import br.com.fiap.api_diploma.model.Diploma;
import br.com.fiap.api_diploma.model.Diplomado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DiplomaRepository extends JpaRepository<Diploma, UUID> {
    Optional<Diploma> findByDiplomadoAndCurso(Diplomado diplomado, Curso curso);
    boolean existsByCurso(Curso curso);
    boolean existsByDiplomado(Diplomado diplomado);
}