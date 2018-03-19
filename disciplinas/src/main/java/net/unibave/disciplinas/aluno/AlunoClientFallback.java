package net.unibave.disciplinas.aluno;

import java.util.ArrayList;

import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Component;

@Component
public class AlunoClientFallback implements AlunoService {

	@Override
	public Resources<AlunoDto> getAllAlunos() {
		return new Resources<>(new ArrayList<>());
	}

}