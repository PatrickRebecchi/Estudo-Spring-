package com.estudo.anime.service;

import com.estudo.anime.dto.request.UsuarioRequestDTO;
import com.estudo.anime.dto.response.AnimeResponseDTO;
import com.estudo.anime.entity.Usuario;
import com.estudo.anime.exception.AnimeException;
import com.estudo.anime.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
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


    public UsuarioRequestDTO cadastrar(UsuarioRequestDTO dto) {
        if (repository.existsByEmail(dto.email())) {
            throw new AnimeException("Email já cadastrado!");
        }
        Usuario u = new Usuario(dto);

        u = repository.save(u);
        return new UsuarioRequestDTO(u.getNome(), u.getEmail());

    }
}
