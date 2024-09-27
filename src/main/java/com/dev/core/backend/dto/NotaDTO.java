package com.dev.core.backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class NotaDTO {
    private Long id;
    private String titulo;
    private String descricao;
    private Long pessoaId;
    private Boolean ehFavorito;
    private String cor;
}