package com.estudo.anime.service;

import com.estudo.anime.dto.request.AnimeRequestDTO;
import com.estudo.anime.dto.response.AnimeResponseDTO;
import com.estudo.anime.entity.Anime;
import com.estudo.anime.exception.EstudosException;
import com.estudo.anime.repository.AnimeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnimeService {

    @Autowired
    private AnimeRepository repository;

    @Transactional
    public List<AnimeResponseDTO> obterAnimes() {
        return converteDados(repository.findAll());
    }

    @Transactional
    public AnimeRequestDTO criarAnime(AnimeRequestDTO dto) {
        Anime anime = new Anime(dto);
        repository.save(anime);
        return new AnimeRequestDTO(
                anime.getNome()
        );
   }

    private List<AnimeResponseDTO> converteDados(List<Anime> animes) {
        return animes.stream()
                .map(c -> new AnimeResponseDTO(
                        c.getId(),
                        c.getNome()))
                .collect(Collectors.toList());
    }

    @Transactional
    public void deletarAnime(long id) {
        repository.deleteById(id);
    }

    @Transactional
    public AnimeRequestDTO atualizarDados(Long id, AnimeRequestDTO dto) {
        Anime anime = repository.findById(id)
                .orElseThrow(() -> new EstudosException("Anime não encontrado"));

        anime.setNome(dto.nome());

        return new AnimeRequestDTO(
                anime.getNome()
        );
    }
}
