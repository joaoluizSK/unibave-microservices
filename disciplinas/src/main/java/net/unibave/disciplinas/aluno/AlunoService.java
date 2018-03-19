package net.unibave.disciplinas.aluno;

import org.springframework.hateoas.Resources;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "alunos", fallback = AlunoClientFallback.class)
public interface AlunoService {

    @RequestMapping(value = "/alunos", method = RequestMethod.GET)
    Resources<AlunoDto> getAllAlunos();
}
