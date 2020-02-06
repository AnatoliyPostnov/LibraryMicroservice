package com.postnov.clientService.Service.EntityService.Impl;

import com.postnov.clientService.Dto.ClientDto;
import com.postnov.clientService.Dto.LibraryCardDto;
import com.postnov.clientService.Entity.Client;
import com.postnov.clientService.Entity.LibraryCard;
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

    public LibraryCardServiceImpl(
            LibraryCardRepository libraryCardRepository,
            ClientService clientService,
            ConvertService<LibraryCardDto, LibraryCard> convertServiceLibraryCard) {
        this.libraryCardRepository = libraryCardRepository;
        this.clientService = clientService;
        this.convertServiceLibraryCard = convertServiceLibraryCard;
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
        Client client = clientService.getClientByPassportNumberAndSeries(number, series);
        libraryCardRepository.deleteByClientId(client.getId());
        clientService.deleteClient(client);
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

    @Override
    public LibraryCardDto getLibraryCardDtoByPassportNumberAndSeries(String number, String series) throws FindPassportByPassportNumberAndSeriesWasNotFoundException {
        LibraryCardDto libraryCardDto = convertServiceLibraryCard
                .convertToDto(
                        getLibraryCardByPassportNumberAndSeries(number, series),
                        LibraryCardDto.class);
        libraryCardDto.setClient(clientService.getClientDtoByPassportNumberAndSeries(number, series));
        return libraryCardDto;
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
        return getLibraryCardByClientId(
                clientService.getClientByPassportNumberAndSeries(number, series).getId());
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
        Client client = clientService.getClientByPassportNumberAndSeries(
                libraryCardDto.getClient().getPassport().getNumber(),
                libraryCardDto.getClient().getPassport().getSeries());
        return getLibraryCardByClientId(client.getId()).getId();
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

    @Override
    public List<LibraryCardDto> getLibraryCards(Long fromLibraryCardsId, Long toLibraryCardId) {
        List<LibraryCardDto> libraryCardsDto = new ArrayList<>();
        for (Long i = fromLibraryCardsId; i <= toLibraryCardId; ++i) {
            try {
                libraryCardsDto.add(getLibraryCardDtoById(i));
            } catch (Exception e) {
                logger.info(e.getMessage());
            }
        }
        return libraryCardsDto;
    }
}
