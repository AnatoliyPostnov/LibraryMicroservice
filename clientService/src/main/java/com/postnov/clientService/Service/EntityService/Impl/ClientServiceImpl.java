package com.postnov.clientService.Service.EntityService.Impl;


import com.postnov.clientService.Dto.ClientDto;
import com.postnov.clientService.Dto.PassportDto;
import com.postnov.clientService.Entity.Client;
import com.postnov.clientService.Entity.Passport;
import com.postnov.clientService.Exception.notFoundException.FindClientByIdWasNotFoundException;
import com.postnov.clientService.Exception.notFoundException.FindClientByPassportIdWasNotFoundException;
import com.postnov.clientService.Exception.notFoundException.FindPassportByPassportNumberAndSeriesWasNotFoundException;
import com.postnov.clientService.Repository.ClientRepository;
import com.postnov.clientService.Service.EntityService.ClientService;
import com.postnov.clientService.Service.EntityService.PassportService;
import com.postnov.clientService.Service.OtherService.ConvertService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    private final PassportService passportService;

    private final ConvertService<ClientDto, Client> convertServiceClient;

    public ClientServiceImpl(ClientRepository clientRepository,
                             PassportService passportService,
                             ConvertService<ClientDto, Client> convertServiceClient) {
        this.clientRepository = clientRepository;
        this.passportService = passportService;
        this.convertServiceClient = convertServiceClient;
    }

    @Transactional
    @Override
    public void deleteClient(Client client) {
        clientRepository.delete(client);
        passportService.deletePassportByPassportId(client.getPassportId());
    }

    @Override
    public ClientDto getClientDtoById(Long Id) {
        return makeClientDto(getClientById(Id));
    }

    @Override
    public ClientDto makeClientDto(Client client) {
        PassportDto passportDto = passportService.getPassportDtoById(client.getPassportId());
        ClientDto clientDto = convertServiceClient.convertToDto(client, ClientDto.class);
        clientDto.setPassport(passportDto);
        return clientDto;
    }

    @Override
    public ClientDto getClientDtoByPassportNumberAndSeries(String number, String series) throws FindPassportByPassportNumberAndSeriesWasNotFoundException {
        return makeClientDto(getClientByPassportNumberAndSeries(number, series));
    }

    @Transactional
    @Override
    public Client save(ClientDto clientDto) {
        Client client = convertServiceClient.convertFromDto(clientDto, Client.class);
        client.setPassportId(passportService.save(clientDto.getPassport()).getId());
        return clientRepository.save(client);
    }

    @Override
    public Client getClientByPassportId(Long passportId) {
        return clientRepository.findClientByPassportId(passportId).orElseThrow(
                () -> new FindClientByPassportIdWasNotFoundException(passportId));
    }

    @Override
    public Client getClientById(Long Id) {
        return clientRepository.findClientById(Id).orElseThrow(
                () -> new FindClientByIdWasNotFoundException(Id));
    }

    @Override
    public Client getClientByPassportNumberAndSeries(String number, String series)
            throws FindPassportByPassportNumberAndSeriesWasNotFoundException {
        Passport passport = passportService.getPassportByPassportNumberAndSeries(number, series);
        return getClientByPassportId(passport.getId());
    }

}