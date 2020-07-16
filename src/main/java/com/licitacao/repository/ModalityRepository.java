package com.licitacao.repository;

import com.licitacao.domain.Modality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Optional;

public interface ModalityRepository extends JpaRepository<Modality, Long> {

    @Modifying
    @Transactional
    @Query(value = "update modality m set m.description = :description WHERE m.id = :id", nativeQuery = true)
    void updateModality(String description, Long id);

    Optional<Modality> findByDescription(String description);
}
