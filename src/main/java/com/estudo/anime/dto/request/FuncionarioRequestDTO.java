package com.estudo.anime.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

public record FuncionarioRequestDTO(@NotBlank(message = "Nome é obrigatório")
                                    String nome,
                                    @Email(message = "Email inválido")
                                    @NotBlank(message = "Email é obrigatório")
                                    String email,
                                    @NotBlank(message = "CPF é obrigatório")
                                    @CPF(message = "CPF inválido")
                                    String cpf,
                                    @Min(value = 0, message = "A idade não pode ser negativa")
                                    Integer idade

) {
}
