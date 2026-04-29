package com.estudo.anime.validation;


import com.estudo.anime.exception.EstudosException;
import com.estudo.anime.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoDeletarFuncionario implements ValidacaoFuncionarioDeletar {

    @Autowired
    private FuncionarioRepository repository;

    @Override
    public void validar(Long id) {

        if(!repository.existsById(id)){
            throw new EstudosException("Cliente não encontrado");
        }

    }
}
