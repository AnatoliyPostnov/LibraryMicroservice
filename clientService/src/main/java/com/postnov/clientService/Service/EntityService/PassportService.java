package com.postnov.clientService.Service.EntityService;

import com.postnov.clientService.Dto.PassportDto;
import com.postnov.clientService.Entity.Passport;
import com.postnov.clientService.Exception.notFoundException.FindPassportByPassportNumberAndSeriesWasNotFoundException;

public interface PassportService {

    void deletePassportByPassportId(Long Id);

    PassportDto getPassportDtoById(Long Id);

    PassportDto makePassportDto(Passport passport);

    Passport getPassportById(Long Id);

    Passport save(PassportDto passportDto);

    Passport getPassportByPassportNumberAndSeries(String number, String series)
            throws FindPassportByPassportNumberAndSeriesWasNotFoundException;

}
