package com.estudo.anime.controller;

import com.estudo.anime.dto.request.FuncionarioRequestDTO;
import com.estudo.anime.dto.response.FuncionarioResponseDTO;
import com.estudo.anime.repository.FuncionarioRepository;
import com.estudo.anime.service.FuncionarioService;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import com.estudo.anime.util.DateUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/funcionarios")
@Log4j2
public class FuncionarioController {
   @Autowired
   private FuncionarioService service;
   @Autowired
   private DateUtil dateUtil;

   @GetMapping
    public List<FuncionarioRequestDTO> obterTodos(){
       log.info(dateUtil.formatLocalDateTimeToDatabase(LocalDateTime.now()));
       return service.obterTodos();
    }

    @PostMapping
    public ResponseEntity<FuncionarioResponseDTO> cadastrar(@RequestBody @Valid FuncionarioRequestDTO dto){
        log.info(dateUtil.formatLocalDateTimeToDatabase(LocalDateTime.now()));
        return ResponseEntity.status(HttpStatus.CREATED)
               .body(service.cadastrar(dto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Map<String, String>> deletarFuncionario(@PathVariable long id){
       service.excluirFuncionario(id);
       return ResponseEntity.ok(Map.of("Mensagem", "Cliente deletado com sucesso"));
    }
}
