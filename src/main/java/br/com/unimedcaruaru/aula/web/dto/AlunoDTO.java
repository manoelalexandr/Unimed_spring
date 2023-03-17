package br.com.unimedcaruaru.aula.web.dto;

import br.com.unimedcaruaru.aula.domain.Aluno;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.Objects;

@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class AlunoDTO {

    private Long id;
    private String name;
    private LocalDate dtNascimento;
    private Integer Idade;

    public static AlunoDTO of(Aluno aluno){
        AlunoDTO alunoDTO = new AlunoDTO();
        alunoDTO.setId(aluno.getId());
        alunoDTO.setName(aluno.getName());
        alunoDTO.setDtNascimento(aluno.getDtNascimento());
        if(Objects.nonNull(aluno.getDtNascimento())) {
            Period periodo = Period.between(aluno.getDtNascimento(), LocalDate.now());
            alunoDTO.setIdade(periodo.getYears());
        }
        return alunoDTO;
    }


}
