package com.estudo.anime.service;

import com.estudo.anime.dto.request.UsuarioRequestDTO;
import com.estudo.anime.entity.Usuario;
import com.estudo.anime.exception.EstudosException;
import com.estudo.anime.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository repository;

    @Transactional
    public List<UsuarioRequestDTO> obterUsuario() {
        return converteDados(repository.findAll());
    }

    private List<UsuarioRequestDTO> converteDados(List<Usuario> usuarios) {
        return usuarios.stream()
                .map(c -> new UsuarioRequestDTO(
                        c.getNome(),
                        c.getEmail()))
                .collect(Collectors.toList());
    }

    @Transactional
    public UsuarioRequestDTO cadastrar(UsuarioRequestDTO dto) {
        if (repository.existsByEmail(dto.email())) { // vejo se existe o Email repetido no banco
            throw new EstudosException("Email já cadastrado!");  // se existi ele retornar o erro falando que já existe
        }
        Usuario u = new Usuario(dto);  // puxo os dados da requisição

        u = repository.save(u);
        return new UsuarioRequestDTO(u.getNome(), u.getEmail()); // // salvo no banco de dados
    }

    public UsuarioRequestDTO AlterarDados(Long id, UsuarioRequestDTO dto) {
        Usuario u = repository.findById(id) // vejo se existe o ID que puxei no banco de dados
                .orElseThrow(() -> new EstudosException("Usuario não encontrado")); // se não achar o id no banco, retornar esse erro e para o codigo

        u.setNome(dto.nome()); //aqui eu puxo o dado que a requisicao envia
        u.setEmail(dto.email()); //aqui eu puxo o dado que a requisicao envia

        repository.save(u); //Aqui eu salvo no banco de dados

        return new UsuarioRequestDTO(
                u.getNome(),
                u.getEmail()
        ); // aqui salva o usuario com os novos dados
    }
}
