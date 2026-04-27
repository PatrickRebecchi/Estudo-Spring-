package com.estudo.anime.entity;

import com.estudo.anime.dto.request.AnimeRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "animes")
@AllArgsConstructor
@NoArgsConstructor
public class Anime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    public Anime(AnimeRequestDTO dto){
        this.nome =  dto.nome();
    }
}
