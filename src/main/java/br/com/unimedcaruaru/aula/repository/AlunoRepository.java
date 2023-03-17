package br.com.unimedcaruaru.aula.repository;

import br.com.unimedcaruaru.aula.domain.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

}
