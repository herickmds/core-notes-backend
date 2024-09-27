package com.dev.core.backend.service;

import com.dev.core.backend.dto.NotaDTO;
import com.dev.core.backend.mapper.NotaMapper;
import com.dev.core.backend.model.Nota;
import com.dev.core.backend.model.Pessoa;
import com.dev.core.backend.repository.NotaRepository;
import com.dev.core.backend.repository.PessoaRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class NotaServiceTests {

    @Autowired
    private NotaService notaService;

    @MockBean
    private NotaRepository notaRepository;
    @MockBean
    private PessoaRepository pessoaRepository;

    @Test
    public void testCreateTask() {
        Nota nota = new Nota();
        nota.setTitulo("Test Nota");
        nota.setDescricao("Test Description");
        Pessoa pessoa = new Pessoa(1L);
        nota.setPessoa(pessoa);

         Mockito.when(pessoaRepository.findById(1L)).thenReturn(Optional.of(pessoa));
        Mockito.when(notaRepository.save(any(Nota.class))).thenReturn(nota);

        NotaDTO notaDTO = NotaMapper.toDto(nota);
        notaDTO.setPessoaId(1L);

        NotaDTO createdNota = notaService.createNota(notaDTO);

        assertEquals("Test Nota", createdNota.getTitulo());
        assertEquals("Test Description", createdNota.getDescricao());
    }

    @Test
    public void testGetTaskById() {
        Nota nota = new Nota();
        nota.setId(1L);
        nota.setTitulo("Test Nota");

        Mockito.when(notaRepository.findById(1L)).thenReturn(Optional.of(nota));

        NotaDTO foundTask = notaService.getNotaById(1L);

        assertEquals("Test Nota", foundTask.getTitulo());
    }
}
