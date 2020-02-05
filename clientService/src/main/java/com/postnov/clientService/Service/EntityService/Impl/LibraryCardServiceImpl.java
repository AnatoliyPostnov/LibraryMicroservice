package com.postnov.clientService.Service.EntityService.Impl;

import com.postnov.clientService.Dto.ClientDto;
import com.postnov.clientService.Dto.LibraryCardDto;
import com.postnov.clientService.Dto.PassportDto;
import com.postnov.clientService.Entity.Client;
import com.postnov.clientService.Entity.LibraryCard;
import com.postnov.clientService.Entity.Passport;
import com.postnov.clientService.Exception.notFoundException.FindLibraryCardByClientIdWasNotFoundException;
import com.postnov.clientService.Exception.notFoundException.FindLibraryCardByIdWasNotFoundException;
import com.postnov.clientService.Exception.notFoundException.FindPassportByPassportNumberAndSeriesWasNotFoundException;
import com.postnov.clientService.Repository.LibraryCardRepository;
import com.postnov.clientService.Service.EntityService.ClientService;
import com.postnov.clientService.Service.EntityService.LibraryCardService;
import com.postnov.clientService.Service.OtherService.ConvertService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class LibraryCardServiceImpl implements LibraryCardService {

    private final Logger logger = LoggerFactory.getLogger(LibraryCardServiceImpl.class);

    private final LibraryCardRepository libraryCardRepository;

    private final ClientService clientService;

    private final ConvertService<LibraryCardDto, LibraryCard> convertServiceLibraryCard;

    private final ConvertService<ClientDto, Client> convertServiceClient;

    private final ConvertService<PassportDto, Passport> convertServicePassport;

    public LibraryCardServiceImpl(LibraryCardRepository libraryCardRepository,
                                  ClientService clientService,
                                  ConvertService<LibraryCardDto, LibraryCard> convertServiceLibraryCard,
                                  ConvertService<ClientDto, Client> convertServiceClient,
                                  ConvertService<PassportDto, Passport> convertServicePassport) {
        this.libraryCardRepository = libraryCardRepository;
        this.clientService = clientService;
        this.convertServiceLibraryCard = convertServiceLibraryCard;
        this.convertServiceClient = convertServiceClient;
        this.convertServicePassport = convertServicePassport;
    }

    @Override
    public void saveLibraryCards(List<LibraryCardDto> libraryCardsDto) {
        for (LibraryCardDto libraryCardDto : libraryCardsDto) {
            save(libraryCardDto);
        }
    }

    @Transactional
    @Override
    public void deleteLibraryCard(String number, String series)
            throws FindPassportByPassportNumberAndSeriesWasNotFoundException {
        Map<String, Object> clientWithPassport =
                clientService.getMapClientWithPassportByPassportNumberAndSeries(number, series);
        Client client = (Client) clientWithPassport.get("Client");
        libraryCardRepository.deleteByClientId(client.getId());
        clientService.deleteClient(client);
    }

    @Override
    public Map<String, Object> getMapLibraryCardWithLibraryCardDtoByPassportNumberAndSeries(String number, String series)
            throws FindPassportByPassportNumberAndSeriesWasNotFoundException {
        Map<String, Object> mapLibraryCardWithClientWithPassport =
                getMapLibraryCardWithClientWithPassportByPassportNumberAndSeries(number, series);

        ClientDto clientDto = convertServiceClient.convertToDto(
                (Client) mapLibraryCardWithClientWithPassport.get("Client"), ClientDto.class);
        clientDto.setPassport(convertServicePassport.convertToDto(
                (Passport) mapLibraryCardWithClientWithPassport.get("Passport"), PassportDto.class));

        LibraryCardDto libraryCardDto =
                convertServiceLibraryCard.convertToDto(
                        (LibraryCard) mapLibraryCardWithClientWithPassport.get("LibraryCard"),
                        LibraryCardDto.class);
        libraryCardDto.setClient(clientDto);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("LibraryCard", mapLibraryCardWithClientWithPassport.get("LibraryCard"));
        resultMap.put("LibraryCardDto", libraryCardDto);

        return resultMap;
    }

    @Override
    public LibraryCardDto getLibraryCardDtoById(Long id) {
        LibraryCard libraryCard = getLibraryCardById(id);
        ClientDto clientDto = clientService.getClientDtoById(libraryCard.getClientId());
        LibraryCardDto libraryCardDto = convertServiceLibraryCard
                .convertToDto(libraryCard, LibraryCardDto.class);
        libraryCardDto.setClient(clientDto);
        return libraryCardDto;
    }

    @Transactional
    @Override
    public Optional<LibraryCard> save(LibraryCardDto libraryCardDto) {
        try {
            getLibraryCardIdByLibraryCardDto(libraryCardDto);
        } catch (FindPassportByPassportNumberAndSeriesWasNotFoundException e) {
            LibraryCard libraryCard = convertServiceLibraryCard
                    .convertFromDto(libraryCardDto, LibraryCard.class);
            libraryCard.setClientId(clientService.save(libraryCardDto.getClient()).getId());
            return Optional.of(libraryCardRepository.save(libraryCard));
        }
        logger.info(" [x] libraryCard: " + libraryCardDto.toString() + " already exist in database");
        return Optional.empty();
    }

    @Transactional
    @Override
    public LibraryCard getLibraryCardById(Long Id) {
        return libraryCardRepository.findLibraryCardById(Id).orElseThrow(
                () -> new FindLibraryCardByIdWasNotFoundException(Id));
    }

    @Override
    public LibraryCard getLibraryCardByPassportNumberAndSeries(String number, String series)
            throws FindPassportByPassportNumberAndSeriesWasNotFoundException {
        Map<String, Object> mapLibraryCardWithClientWithPassport =
                getMapLibraryCardWithClientWithPassportByPassportNumberAndSeries(number, series);
        return (LibraryCard) mapLibraryCardWithClientWithPassport.get("LibraryCard");
    }

    @Transactional
    @Override
    public LibraryCard getLibraryCardByClientId(Long clientId) {
        return libraryCardRepository.findLibraryCardByClientId(
                clientId).orElseThrow(
                () -> new FindLibraryCardByClientIdWasNotFoundException(clientId));
    }

    @Override
    public Long getLibraryCardIdByLibraryCardDto(LibraryCardDto libraryCardDto)
            throws FindPassportByPassportNumberAndSeriesWasNotFoundException {
        Client client = (Client) getMapLibraryCardWithClientWithPassportByPassportNumberAndSeries(
                libraryCardDto.getClient().getPassport().getNumber(),
                libraryCardDto.getClient().getPassport().getSeries())
                .get("Client");
        return getLibraryCardByClientId(client.getId()).getId();
    }

    @Override
    public Set<LibraryCardDto> getLibraryCards(Long fromLibraryCardsId, Long toLibraryCardId) {
        Set<LibraryCardDto> libraryCardsDto = new HashSet<>();
        for (Long i = fromLibraryCardsId; i <= toLibraryCardId; ++i) {
            try {
                libraryCardsDto.add(getLibraryCardDtoById(i));
            } catch (Exception e) {
                logger.info(e.getMessage());
            }
        }
        return libraryCardsDto;
    }

    @Override
    public Map<String, Object> getMapLibraryCardWithClientWithPassportByPassportNumberAndSeries(
            String number, String series)
            throws FindPassportByPassportNumberAndSeriesWasNotFoundException {
        Map<String, Object> resultMap =
                clientService.getMapClientWithPassportByPassportNumberAndSeries(number, series);
        Client client = (Client) resultMap.get("Client");
        LibraryCard libraryCard = getLibraryCardByClientId(client.getId());
        resultMap.put("LibraryCard", libraryCard);
        return resultMap;
    }

}
