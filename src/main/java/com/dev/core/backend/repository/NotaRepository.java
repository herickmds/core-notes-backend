package com.dev.core.backend.repository;

import com.dev.core.backend.model.Nota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotaRepository extends JpaRepository<Nota, Long> {
    @Query("SELECT n FROM Nota n WHERE (:favorito IS NULL OR n.ehFavorito = :favorito) " +
            "AND (:cor IS NULL OR n.cor = :cor) " +
            "AND (:filtro IS NULL OR LOWER(n.titulo) LIKE LOWER(CONCAT('%', :filtro, '%')) " +
            "OR LOWER(n.descricao) LIKE LOWER(CONCAT('%', :filtro, '%')))")
    List<Nota> findByFilters(@Param("favorito") Boolean favorito,
                             @Param("cor") String cor,
                             @Param("filtro") String filtro);
}
