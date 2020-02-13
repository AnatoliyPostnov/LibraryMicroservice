package com.postnov.clientService.Service.EntityService.Impl;

import com.postnov.clientService.Dto.PassportDto;
import com.postnov.clientService.Entity.Passport;
import com.postnov.clientService.Exception.notFoundException.FindPassportByIdWasNotFoundException;
import com.postnov.clientService.Exception.notFoundException.FindPassportByPassportNumberAndSeriesWasNotFoundException;
import com.postnov.clientService.Repository.PassportRepository;
import com.postnov.clientService.Service.EntityService.PassportService;
import com.postnov.clientService.Service.OtherService.ConvertService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class PassportServiceImpl implements PassportService {

    private final PassportRepository passportRepository;

    private final ConvertService<PassportDto, Passport> convertServicePassport;

    public PassportServiceImpl(PassportRepository passportRepository,
                               ConvertService<PassportDto, Passport> convertServicePassport) {
        this.passportRepository = passportRepository;
        this.convertServicePassport = convertServicePassport;
    }

    @Transactional
    @Override
    public void deletePassportByPassportId(Long Id) {
        passportRepository.deleteById(Id);
    }

    @Override
    public PassportDto getPassportDtoById(Long Id) {
        return makePassportDto(getPassportById(Id));
    }

    @Override
    public PassportDto makePassportDto(Passport passport) {
        return convertServicePassport.convertToDto(passport, PassportDto.class);
    }

    @Transactional
    @Override
    public Passport save(PassportDto passportDto) {
        Passport passport = convertServicePassport.convertFromDto(passportDto, Passport.class);
        return passportRepository.save(passport);
    }

    @Transactional
    @Override
    public Passport getPassportByPassportNumberAndSeries(String number, String series)
            throws FindPassportByPassportNumberAndSeriesWasNotFoundException {
        return passportRepository.findPassportByNumberAndSeries(number, series).orElseThrow(
                () -> new FindPassportByPassportNumberAndSeriesWasNotFoundException(number, series));
    }

    @Transactional
    @Override
    public Passport getPassportById(Long Id) {
        return passportRepository.findPassportById(Id).orElseThrow(
                () -> new FindPassportByIdWasNotFoundException(Id));
    }
}
