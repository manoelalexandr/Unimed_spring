package br.com.unimedcaruaru.aula.controller;

import br.com.unimedcaruaru.aula.domain.Aluno;
import br.com.unimedcaruaru.aula.repository.AlunoRepository;
import br.com.unimedcaruaru.aula.web.dto.AlunoCreateDTO;
import br.com.unimedcaruaru.aula.web.dto.AlunoDTO;
import jdk.vm.ci.meta.Local;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/aula")
public class AlunoController {

    private final AlunoRepository alunoRepository;

    @GetMapping("/aluno/{id}")
    public AlunoDTO alunoById(@PathVariable("id") Long id){
        return alunoRepository.findById(id)
                .map(AlunoDTO::of)
                .orElseThrow(() -> new RuntimeException("Not found"));
    }
    @GetMapping("/alunos")
    public List<AlunoDTO> aluno(Pageable pageable){

        List<AlunoDTO> alunoDTOS =  this.alunoRepository.findAll(pageable).stream()
                .map(AlunoDTO::of)
                .collect(Collectors.toList());
        /*
        JEITO MAIS LONGO E MENOS ATUAL DE FAZER O QUE ESTA EM CIMA

        List<Aluno> alunos =  this.alunoRepository.findAll()
        List<AlunoDTO> alunoDTOS = new ArrayList<>();
        for(Aluno aluno : alunos){
            AlunoDTO alunoDTO = new AlunoDTO();
            alunoDTO.setId(aluno.getId());
            alunoDTO.setName(aluno.getName());
            alunoDTO.setDtNascimento(aluno.getDtNascimento());
            if(Objects.nonNull(aluno.getDtNascimento())) {
                Period periodo = Period.between(aluno.getDtNascimento(), LocalDate.now());
                alunoDTO.setIdade(periodo.getYears());
            }
            alunoDTOS.add(alunoDTO);
        }
         */
        return alunoDTOS;
    }
    @PostMapping("/aluno")
    public AlunoDTO alunoAdd(@RequestBody AlunoCreateDTO dto){


        AlunoDTO alunoDTO = Optional.of(dto)
                .map(AlunoCreateDTO::to)
                .map(this.alunoRepository::save)
                .map(AlunoDTO::of)
                .orElseThrow(() -> new RuntimeException("Erro interno"));
        /*
        --- OPÇÃO INTERESSANTE ---
        Aluno aluno = AlunoCreateDTO.to(dto);
        this.alunoRepository.save(aluno);
         */

        /*
        --- OPÇÃO MAIS ANTIGA ---
        Aluno aluno = new Aluno();
        aluno.setName(dto.getName());
        aluno.setSenha(dto.getSenha());
        aluno.setDtNascimento(LocalDate.parse(dto.getDtNascimento()));
        alunoRepository.save(aluno);

        ---- ISSO NÃO TA IMPLEMENTADO AINDA ----
          AlunoDTO alunoDTO = AlunoDTO.builder()
                .name("")
                .dtNascimento(LocalDate.now())
                .build();

        AlunoDTO alunos = new AlunoDTO();
        alunos.setId(aluno.getId());
        alunos.setName(aluno.getName());
        alunos.setDtNascimento(aluno.getDtNascimento());
        */
        return alunoDTO;
    }

    @PutMapping("/aluno/{id}")
    public void alunoPut (@PathVariable("id") Long id, @RequestBody AlunoCreateDTO newAluno){
        Optional<Aluno> oldAluno = alunoRepository.findById(id);
        if(oldAluno.isPresent()){
            Aluno aluno = oldAluno.get();
            if(Objects.nonNull(newAluno.getName())) {
                aluno.setName(newAluno.getName());
            }
            if(Objects.nonNull(newAluno.getSenha())) {
                aluno.setSenha(newAluno.getSenha());
            }
            alunoRepository.save(aluno);
        }
    }

    @DeleteMapping("/aluno/{id}")
    public void alunoDelete(@PathVariable("id") Long id) {
        alunoRepository.deleteById(id);
    }
}