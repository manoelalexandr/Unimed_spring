package br.com.unimedcaruaru.aula.controller;

import br.com.unimedcaruaru.aula.domain.Turma;
import br.com.unimedcaruaru.aula.repository.TurmaRepository;
import br.com.unimedcaruaru.aula.web.dto.TurmaDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class TurmaController {

    private final TurmaRepository turmaRepository;

    @GetMapping("/turma/{id}")
    public TurmaDTO alunoById(@PathVariable("id") Long id){
        return turmaRepository.findById(id)
                .map(TurmaDTO::of)
                .orElseThrow(() -> new RuntimeException("Not found"));
    }

    @GetMapping("/turmas")
    public List<TurmaDTO> turma(Pageable pageable){

        List<TurmaDTO> turmaDTOS =  this.turmaRepository.findAll(pageable).stream()
                .map(TurmaDTO::of)
                .collect(Collectors.toList());
        return turmaDTOS;
    }

    @PostMapping("/turma")
    public TurmaDTO turmaAdd(@RequestBody TurmaDTO dto) {

        TurmaDTO turmaDTO = Optional.of(dto)
                .map(TurmaDTO::to)
                .map(this.turmaRepository::save)
                .map(TurmaDTO::of)
                .orElseThrow(() -> new RuntimeException("Erro interno"));

        return turmaDTO;
    }
    @DeleteMapping("/turma/{id}")
    public void turmaDelete(@PathVariable("id") Long id) {
        turmaRepository.deleteById(id);
    }

    @PutMapping("/turma/{id}")
    public void turmaPut (@PathVariable("id") Long id, @RequestBody TurmaDTO newTurma){
        Optional<Turma> oldTurma = turmaRepository.findById(id);
        if(oldTurma.isPresent()){
            Turma turma = oldTurma.get();
            if(Objects.nonNull(newTurma.getCurso())) {
                turma.setCurso(newTurma.getCurso());
            }
            if(Objects.nonNull(newTurma.getCargaHoraria())) {
                turma.setCargaHoraria(newTurma.getCargaHoraria());
            }
            turmaRepository.save(turma);
        }
    }
}
