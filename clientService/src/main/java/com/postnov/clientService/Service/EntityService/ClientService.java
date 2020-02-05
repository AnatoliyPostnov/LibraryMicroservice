package com.postnov.clientService.Service.EntityService;


import com.postnov.clientService.Dto.ClientDto;
import com.postnov.clientService.Entity.Client;
import com.postnov.clientService.Exception.notFoundException.FindPassportByPassportNumberAndSeriesWasNotFoundException;

import java.util.Map;

public interface ClientService {

    void deleteClient(Client client);

    ClientDto getClientDtoById(Long Id);

    ClientDto makeClientDto(Client client);

    Client save(ClientDto clientDto);

    Client getClientByPassportId(Long passportId);

    Client getClientById(Long Id);

    Map<String, Object> getMapClientWithPassportByPassportNumberAndSeries(
            String number, String series) throws FindPassportByPassportNumberAndSeriesWasNotFoundException;

}
