package br.com.fiap.api_diploma.repository;

import br.com.fiap.api_diploma.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
    boolean existsByNome(String nome);
}