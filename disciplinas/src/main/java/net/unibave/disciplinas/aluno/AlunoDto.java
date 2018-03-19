package net.unibave.disciplinas.aluno;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AlunoDto {
    private String nome;
    private String email;
    private Integer matricula;
    private List<String> disciplinas;
}
