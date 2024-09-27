package com.dev.core.backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.dev.core.backend.dto.NotaDTO;
import com.dev.core.backend.service.NotaService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/notas")
public class NotaController {

    private final NotaService notaService;

    @Autowired
    public NotaController(NotaService notaService) {
        this.notaService = notaService;
    }

    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public List<NotaDTO> getAllNotas(
            @RequestParam(required = false) Optional<Boolean> favorito,
            @RequestParam(required = false) Optional<String> cor,
            @RequestParam(required = false) Optional<String> filtro) { // Novo parâmetro para busca por título ou descrição
        return notaService.getAllNotas(favorito, cor, filtro);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public ResponseEntity<NotaDTO> getNotaById(@PathVariable Long id) {
        NotaDTO notaDTO = notaService.getNotaById(id);
        return ResponseEntity.ok(notaDTO);
    }

    @PostMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public ResponseEntity<NotaDTO> createNota(@RequestBody NotaDTO notaDTO) {
        NotaDTO createdNota = notaService.createNota(notaDTO);
        return new ResponseEntity<>(createdNota, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public ResponseEntity<NotaDTO> updateNota(@PathVariable Long id, @RequestBody NotaDTO notaDTO) {
        NotaDTO updatedNota = notaService.updateNota(id, notaDTO);
        return ResponseEntity.ok(updatedNota);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public ResponseEntity<Void> deleteNota(@PathVariable Long id) {
        notaService.deleteNota(id);
        return ResponseEntity.noContent().build();
    }
}
