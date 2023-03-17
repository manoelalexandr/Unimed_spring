package br.com.unimedcaruaru.aula;

import br.com.unimedcaruaru.aula.web.dto.AlunoCreateDTO;
import br.com.unimedcaruaru.aula.web.dto.AlunoDTO;
import org.graalvm.compiler.core.common.util.Util;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;

@SpringBootApplication
public class Aula1Application {
	public static void main(String[] args) {
		SpringApplication.run(Aula1Application.class, args);
	}
}