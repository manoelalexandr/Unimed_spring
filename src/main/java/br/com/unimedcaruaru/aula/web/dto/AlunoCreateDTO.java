package br.com.unimedcaruaru.aula.web.dto;

import br.com.unimedcaruaru.aula.domain.Aluno;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AlunoCreateDTO {

    private String name;
    private String senha;
    private String dtNascimento;

    public static Aluno to(AlunoCreateDTO alunoCreateDTO){
        Aluno aluno = new Aluno();
        aluno.setName(alunoCreateDTO.getName());
        aluno.setSenha(alunoCreateDTO.getSenha());
        aluno.setDtNascimento(LocalDate.parse(alunoCreateDTO.getDtNascimento()));

        return aluno;
    }
}
