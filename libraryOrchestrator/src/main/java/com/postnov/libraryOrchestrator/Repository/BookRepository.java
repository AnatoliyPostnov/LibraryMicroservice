package com.postnov.libraryOrchestrator.Repository;

import com.postnov.libraryOrchestrator.Entity.AddBookJson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<AddBookJson, Long> {

}
