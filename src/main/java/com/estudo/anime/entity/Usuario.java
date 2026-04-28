package com.estudo.anime.entity;

import com.estudo.anime.dto.request.UsuarioRequestDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome obrigatorio")
    private String nome;

    @NotBlank(message = "Email obrigatorio")
    private String email;

    public Usuario(UsuarioRequestDTO dto) {
        this.nome = dto.nome();
        this.email = dto.email();
    }
}
