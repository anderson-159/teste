package br.com.letscode.teste.dto;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;

@Data
@Builder

public class ClasseDTO {
    private String uid;

    @NotBlank(message = "O nome é obrigatório.")
    @Length(min = 2, message = "O nome tem que ter no mínimo 2 letras.")
    private String nome;
}

