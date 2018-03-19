package net.unibave.disciplinas.disciplina;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.unibave.disciplinas.aluno.AlunoDto;
import net.unibave.disciplinas.aluno.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/disciplinas")
public class DisciplinaController {

    @Autowired
    DiscoveryClient discoveryClient;

    @Autowired
    AlunoService alunoService;

    @GetMapping
    public DisciplinaDto listar() throws Exception {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        Resources<AlunoDto> alunos = alunoService.getAllAlunos();
        List<String> alunosNomes = alunos.getContent().stream()
                .map(a -> a.getNome()).collect(Collectors.toList());

        return DisciplinaDto.builder()
                .nome("Microservicos")
                .cargaHoraria(60)
                .dataInicio(dateFormat.parse("16/02/2018"))
                .alunosMatriculados(alunosNomes)
                .build();

    }

}
