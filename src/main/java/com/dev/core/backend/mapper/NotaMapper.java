package com.dev.core.backend.mapper;

import com.dev.core.backend.dto.NotaDTO;
import com.dev.core.backend.model.Nota;
import com.dev.core.backend.model.Pessoa;
import org.springframework.stereotype.Component;

@Component
public class NotaMapper {

    public static NotaDTO toDto(Nota nota) {
        if (nota == null) {
            return null;
        }

        NotaDTO notaDTO = new NotaDTO();
        notaDTO.setId(nota.getId());
        notaDTO.setTitulo(nota.getTitulo());
        notaDTO.setDescricao(nota.getDescricao());
        notaDTO.setEhFavorito(nota.getEhFavorito());
        notaDTO.setCor(nota.getCor());
        notaDTO.setPessoaId(nota.getPessoa() != null ? nota.getPessoa().getId() : null);
        return notaDTO;
    }

    public static Nota toEntity(NotaDTO notaDTO) {
        Nota nota = new Nota();
        nota.setId(notaDTO.getId());
        nota.setTitulo(notaDTO.getTitulo());
        nota.setDescricao(notaDTO.getDescricao());
        nota.setEhFavorito(notaDTO.getEhFavorito());
        nota.setCor(notaDTO.getCor());
        nota.setPessoa(new Pessoa(notaDTO.getPessoaId()));

        return nota;
    }
}