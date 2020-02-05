package com.postnov.clientService.Repository;


import com.postnov.clientService.Entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findClientByPassportId(
            @Param("passportId") Long passportId);

    Optional<Client> findClientById(
            @Param("Id") Long Id);

}
