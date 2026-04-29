package com.estudo.anime.service;

import com.estudo.anime.dto.request.FuncionarioRequestDTO;

import com.estudo.anime.dto.response.FuncionarioResponseDTO;
import com.estudo.anime.entity.Funcionario;
import com.estudo.anime.exception.EstudosException;
import com.estudo.anime.repository.FuncionarioRepository;
import com.estudo.anime.validation.ValidacaoFuncionarioCriar;
import com.estudo.anime.validation.ValidacaoFuncionarioDeletar;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository repository;

    @Autowired
    private List<ValidacaoFuncionarioDeletar> validacaoDeletar;


    @Autowired
    private List<ValidacaoFuncionarioCriar> validacao;

    private List<FuncionarioRequestDTO> converteDados(List<Funcionario> funcionarios) {
        return funcionarios.stream()
                .map(f -> new FuncionarioRequestDTO(
                        f.getNome(),
                        f.getEmail(),
                        f.getCpf(),
                        f.getIdade()!= null ? f.getIdade() : 0
                ))
                .collect(Collectors.toList());
    }

    @Transactional
    public List<FuncionarioRequestDTO> obterTodos() {
        return converteDados(repository.findAll());
    }


    @Transactional
    public FuncionarioResponseDTO cadastrar(FuncionarioRequestDTO dto) {
        validacao.forEach(c -> c.validar(dto));
        if (repository.existsByCpf(dto.cpf())) {
            throw new EstudosException("CPF já cadastrado");
        }
            Funcionario funcionario = new Funcionario(dto);

            funcionario = repository.save(funcionario);

            return new FuncionarioResponseDTO(
                    funcionario.getId(),
                    funcionario.getNome(),
                    funcionario.getEmail(),
                    funcionario.getCpf(),
                    funcionario.getIdade()
            );
    }
    @Transactional
    public void excluirFuncionario(long id) {
        validacaoDeletar.forEach(f -> f.validar(id));
        repository.deleteById(id);

    }
}

