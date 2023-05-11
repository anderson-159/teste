package br.com.letscode.teste.dto;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

@Builder
@Data
public class CarroDTO {
    private String uid;
    @NotBlank(message = "O nome é obrigatório.")
    @Length(min = 2, max = 150, message = "O nome do carro tem que ter no mínimo 2 letras.")
    private String nome;
    @Min(value = 0, message = "A placa do carro deve ser maior que zero.")
    @Max(value = 4000, message = "A placa do carro deve ser menor que 4000.")
    private int placa;

    @NotNull(message = "A classe do carro é obrigatória.")
    private ClasseDTO classe;
}

