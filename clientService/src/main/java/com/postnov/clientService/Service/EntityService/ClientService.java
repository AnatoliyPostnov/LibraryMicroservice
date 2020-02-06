package com.postnov.clientService.Service.EntityService;


import com.postnov.clientService.Dto.ClientDto;
import com.postnov.clientService.Entity.Client;
import com.postnov.clientService.Exception.notFoundException.FindPassportByPassportNumberAndSeriesWasNotFoundException;

public interface ClientService {

    void deleteClient(Client client);

    ClientDto getClientDtoById(Long Id);

    ClientDto makeClientDto(Client client);

    ClientDto getClientDtoByPassportNumberAndSeries(String number, String series)
            throws FindPassportByPassportNumberAndSeriesWasNotFoundException;

    Client save(ClientDto clientDto);

    Client getClientByPassportId(Long passportId);

    Client getClientById(Long Id);

    Client getClientByPassportNumberAndSeries(String number, String series)
            throws FindPassportByPassportNumberAndSeriesWasNotFoundException;

}
