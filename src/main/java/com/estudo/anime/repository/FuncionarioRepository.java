package com.estudo.anime.repository;

import com.estudo.anime.entity.Funcionario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    boolean existsByEmail(String email);
}
