package com.estudo.anime.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioRequestDTO(@NotBlank(message = "Nome é obrigatório")
                                String nome,
                                @Email(message = "Email inválido")
                                @NotBlank(message = "Email é obrigatório")
                                String email

) {
}
