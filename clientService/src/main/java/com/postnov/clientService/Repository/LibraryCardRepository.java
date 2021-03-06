package com.postnov.clientService.Repository;

import com.postnov.clientService.Entity.LibraryCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface LibraryCardRepository extends JpaRepository<LibraryCard, Long> {

    Optional<LibraryCard> findLibraryCardByClientId(
            @Param("clientId") Long clientId);

    Optional<LibraryCard> findLibraryCardById(
            @Param("Id") Long Id);

    void deleteByClientId(
            @Param("Id") Long Id);
}
