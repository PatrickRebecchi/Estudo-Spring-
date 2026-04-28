package com.estudo.anime.controller;

import com.estudo.anime.dto.request.UsuarioRequestDTO;
import com.estudo.anime.dto.response.AnimeResponseDTO;
import com.estudo.anime.service.UsuarioService;
import com.estudo.anime.util.DateUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private DateUtil dateUtil;

    @Autowired
    private UsuarioService service;

    @GetMapping
    public List<UsuarioRequestDTO> obterUsuario(){
        return service.obterUsuario();
    }

    @PostMapping
    public ResponseEntity<UsuarioRequestDTO> cadastrar(@RequestBody UsuarioRequestDTO dto){
        log.info(dateUtil.formatLocalDateTimeToDatabase(LocalDateTime.now()));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.cadastrar(dto));
    }

}
