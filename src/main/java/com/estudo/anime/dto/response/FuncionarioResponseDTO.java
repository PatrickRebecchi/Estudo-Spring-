package com.estudo.anime.dto.response;

public record FuncionarioResponseDTO(
        Long id,
        String nome,
        String email,
        String cpf,
        int idade
) {
}
