package com.estudo.anime.entity;

import com.estudo.anime.dto.request.FuncionarioRequestDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "funcionarios")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Nome obrigatorio")
    private String nome;
    @NotBlank(message = "Email obrigatorio")
    private String email;
    @NotBlank(message = "CPF obrigatorio")
    private String cpf;
    private Integer idade;

    public Funcionario(FuncionarioRequestDTO dto){
        this.nome = dto.nome();
        this.email = dto.email();
        this.cpf = dto.cpf();
        this.idade = dto.idade();
    }

}
