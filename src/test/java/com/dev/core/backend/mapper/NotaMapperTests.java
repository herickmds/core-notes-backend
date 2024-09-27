package com.dev.core.backend.mapper;


import com.dev.core.backend.dto.NotaDTO;
import com.dev.core.backend.model.Nota;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NotaMapperTests {

    @Test
    public void testConvertToDTO() {
        Nota nota = new Nota();
        nota.setId(1L);
        nota.setTitulo("Test Nota");
        nota.setDescricao("Test Description");

        NotaDTO notaDTO = NotaMapper.toDto(nota);

        assertEquals(1L, notaDTO.getId());
        assertEquals("Test Nota", notaDTO.getTitulo());
        assertEquals("Test Description", notaDTO.getDescricao());
    }

    @Test
    public void testConvertToEntity() {
        NotaDTO notaDTO = new NotaDTO();
        notaDTO.setId(1L);
        notaDTO.setTitulo("Test Nota");
        notaDTO.setDescricao("Test Description");

        Nota nota = NotaMapper.toEntity(notaDTO);

        assertEquals(1L, nota.getId());
        assertEquals("Test Nota", nota.getTitulo());
        assertEquals("Test Description", nota.getDescricao());
    }
}