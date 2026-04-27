package com.estudo.anime.controller;

import com.estudo.anime.dto.AnimeRequestDTO;
import com.estudo.anime.dto.AnimeResponseDTO;
import com.estudo.anime.entity.Anime;
import com.estudo.anime.service.AnimeService;
import com.estudo.anime.util.DateUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/animes")
@Log4j2
public class AnimeController {

    @Autowired
    private DateUtil dateUtil;
    @Autowired
    private AnimeService service;

    @GetMapping
    public List<AnimeResponseDTO> obterAnimes(){
        return service.obterAnimes();
    }

    @PostMapping
    public ResponseEntity<AnimeRequestDTO> criar(@RequestBody AnimeRequestDTO dto){
        log.info(dateUtil.formatLocalDateTimeToDatabase(LocalDateTime.now()));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.criarAnime(dto));
    }

    @PutMapping("{id}")
    public AnimeRequestDTO alterarDados(@PathVariable Long id, @RequestBody AnimeRequestDTO dto){
        log.info(dateUtil.formatLocalDateTimeToDatabase(LocalDateTime.now()));
        return service.atualizarDados(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deletar(@PathVariable long id){
        log.info(dateUtil.formatLocalDateTimeToDatabase(LocalDateTime.now()));
        service.deletarAnime(id);
        return ResponseEntity.ok(Map.of("Mensagem", "Anime deletado com sucesso"));
    }




}
