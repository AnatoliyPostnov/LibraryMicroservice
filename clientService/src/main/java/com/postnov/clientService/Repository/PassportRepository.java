package com.postnov.clientService.Repository;

import com.postnov.clientService.Entity.Passport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PassportRepository extends JpaRepository<Passport, Long> {

    Optional<Passport> findPassportByNumberAndSeries(
            @Param("number") String number,
            @Param("series") String series);

    Optional<Passport> findPassportById(
            @Param("Id") Long Id);

    void deleteById(
            @Param("Id") Long Id);
}
