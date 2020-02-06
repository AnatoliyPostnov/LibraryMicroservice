package com.postnov.receivedBookService.Repository;

import com.postnov.receivedBookService.Entity.ReceivedBookMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceivedBookMessageRepository extends JpaRepository<ReceivedBookMessage, Long> {
}
