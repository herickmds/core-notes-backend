package com.dev.core.backend.service;

import com.dev.core.backend.dto.NotaDTO;
import com.dev.core.backend.exception.ResourceNotFoundException;
import com.dev.core.backend.model.Nota;
import com.dev.core.backend.model.Pessoa;
import com.dev.core.backend.repository.NotaRepository;
import com.dev.core.backend.repository.PessoaRepository;
import com.dev.core.backend.util.ErrorMessageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.core.backend.mapper.NotaMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NotaService {


    private final NotaRepository notaRepository;

    private final PessoaRepository pessoaRepository;

    private final ErrorMessageManager errorMessageManager;

    @Autowired
    public NotaService(NotaRepository notaRepository, PessoaRepository pessoaRepository, ErrorMessageManager errorMessageManager) {
        this.notaRepository = notaRepository;
        this.pessoaRepository = pessoaRepository;
        this.errorMessageManager = errorMessageManager;
    }
    public List<NotaDTO> getAllNotas(Optional<Boolean> favorito, Optional<String> cor, Optional<String> filtro) {
        List<Nota> notas = notaRepository.findByFilters(favorito.orElse(null), cor.orElse(null), filtro.orElse(""));
        return notas.stream()
                .map(NotaMapper::toDto)
                .collect(Collectors.toList());
    }

    public NotaDTO getNotaById(Long id) {
        Nota nota = notaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        errorMessageManager.getErrorMessage("nota.not.found", id)));
        return NotaMapper.toDto(nota);
    }

    public NotaDTO createNota(NotaDTO notaDTO) {
        Nota nota = NotaMapper.toEntity(notaDTO);
        Pessoa pessoa = pessoaRepository.findById(notaDTO.getPessoaId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        errorMessageManager.getErrorMessage("pessoa.not.found", notaDTO.getPessoaId())));
        nota.setPessoa(pessoa);
        Nota savedNota = notaRepository.save(nota);
        return NotaMapper.toDto(savedNota);
    }

    public NotaDTO updateNota(Long id, NotaDTO notaDTO) {
        Nota existingNota = notaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        errorMessageManager.getErrorMessage("nota.not.found", id)));

        Nota updatedNota = NotaMapper.toEntity(notaDTO);
        updatedNota.setId(existingNota.getId());
        Pessoa pessoa = pessoaRepository.findById(notaDTO.getPessoaId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        errorMessageManager.getErrorMessage("pessoa.not.found", notaDTO.getPessoaId())));
        updatedNota.setPessoa(pessoa);

        Nota savedNota = notaRepository.save(updatedNota);
        return NotaMapper.toDto(savedNota);
    }

    public void deleteNota(Long id) {
        Nota nota = notaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        errorMessageManager.getErrorMessage("nota.not.found", id)));

        notaRepository.delete(nota);
    }
}
