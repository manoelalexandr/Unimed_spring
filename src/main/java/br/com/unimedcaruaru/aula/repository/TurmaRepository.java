package br.com.unimedcaruaru.aula.repository;

import br.com.unimedcaruaru.aula.domain.Turma;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TurmaRepository extends JpaRepository<Turma, Long> {
}
