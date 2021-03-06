package com.postnov.libraryOrchestrator.Repository;

import com.postnov.libraryOrchestrator.Entity.AddLibraryCardJson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryCardRepository extends JpaRepository<AddLibraryCardJson, Long> {
}
