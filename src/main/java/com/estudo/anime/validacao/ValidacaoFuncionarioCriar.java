package com.estudo.anime.validacao;

import com.estudo.anime.dto.request.FuncionarioRequestDTO;
import com.estudo.anime.exception.EstudosException;
import com.estudo.anime.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoFuncionarioCriar implements ValidacaoCriarFuncionario{

    @Autowired
    private FuncionarioRepository repository;

    @Override
    public void validar(FuncionarioRequestDTO dto){
        if (repository.existsByEmail(dto.email())){
            throw new EstudosException("Email já cadastrado (Validacao)");
        }
    }
}
