package com.postnov.libraryOrchestrator.Repository;

import com.postnov.libraryOrchestrator.Entity.ReceivedBookMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceivedBookRepository extends JpaRepository<ReceivedBookMessage, Long> {
}
