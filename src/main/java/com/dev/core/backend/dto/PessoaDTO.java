package com.dev.core.backend.dto;

import com.dev.core.backend.model.Pessoa;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import java.time.LocalDate;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PessoaDTO {
    private Long id;
    private String nome;
    private String nomeUsuario;
    private String email;
    private LocalDate dataNascimento;

    public PessoaDTO() {}

    public PessoaDTO(Pessoa pessoa) {
        this.id = pessoa.getId();
        this.nome = pessoa.getNome();
        this.nomeUsuario = pessoa.getNomeUsuario();
        this.email = pessoa.getEmail();
        this.dataNascimento = pessoa.getDataNascimento();
    }
}