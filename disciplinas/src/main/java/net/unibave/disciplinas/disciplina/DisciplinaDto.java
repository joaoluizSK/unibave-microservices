package net.unibave.disciplinas.disciplina;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
public class DisciplinaDto {

    String nome;
    Integer cargaHoraria;
    Date dataInicio;
    List<String> alunosMatriculados;

}
