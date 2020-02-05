package com.postnov.libraryOrchestrator.Repository;

import com.postnov.libraryOrchestrator.Entity.AddLibraryCardJson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryCardRepository extends JpaRepository<AddLibraryCardJson, Long> {
}
