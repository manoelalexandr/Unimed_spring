package br.com.unimedcaruaru.aula.web.dto;

import br.com.unimedcaruaru.aula.domain.Aluno;
import br.com.unimedcaruaru.aula.domain.Turma;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class TurmaDTO {
    private Long id;
    private String curso;
    private String cargaHoraria;

    public static TurmaDTO of(Turma turma){
        TurmaDTO turmaDTO = new TurmaDTO();
        turmaDTO.setId(turma.getId());
        turmaDTO.setCurso(turma.getCurso());
        turmaDTO.setCargaHoraria(turma.getCargaHoraria());
        return turmaDTO;
    }

    public static Turma to(TurmaDTO turmaDTO){
        Turma turma = new Turma();
        turma.setCurso(turmaDTO.getCurso());
        turma.setCargaHoraria(turmaDTO.getCargaHoraria());

        return turma;
    }
}
