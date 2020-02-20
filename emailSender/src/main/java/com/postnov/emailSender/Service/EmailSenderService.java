package com.postnov.emailSender.Service;


import com.postnov.emailSender.Client.ReceivedBookClient;
import com.postnov.emailSender.Dto.LibraryCardDto;
import com.postnov.emailSender.Dto.ReceivedBookDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@EnableScheduling
public class EmailSenderService {

    private final ReceivedBookClient receivedBookClient;

    private final JavaMailSender sender;

    @Value("${spring.mail.username}")
    private String username;

    public EmailSenderService(
            ReceivedBookClient receivedBookClient,
            JavaMailSender sender) {
        this.receivedBookClient = receivedBookClient;
        this.sender = sender;
    }

    private void send(String mailTo, String subject, String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom(username);
        mailMessage.setTo(mailTo);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);

        sender.send(mailMessage);
    }

    @Scheduled(fixedRate = 10000)
    private void executeTask() throws Exception {
        List<LibraryCardDto> libraryCardsDto = new ArrayList<>();

        for (ReceivedBookDto receivedBookDto : receivedBookClient.getAllReceivedBook()) {
            if (!libraryCardsDto.contains(receivedBookDto.getLibraryCard())) {
                libraryCardsDto.add(receivedBookDto.getLibraryCard());
            }
        }

        for (LibraryCardDto libraryCardDto : libraryCardsDto) {
            List<ReceivedBookDto> receivedBooksDtoByLibraryCard = new ArrayList<>();
//            Строчка для тестирования
//            receivedBooksDtoByLibraryCard = receivedBookClient.getReceivedBooksByPassportNumberAndSeries(
//                    libraryCardDto.getClient().getPassport().getNumber(),
//                    libraryCardDto.getClient().getPassport().getSeries());
            for (ReceivedBookDto receivedBookDto : receivedBookClient.getReceivedBooksByPassportNumberAndSeries(
                    libraryCardDto.getClient().getPassport().getNumber(),
                    libraryCardDto.getClient().getPassport().getSeries())) {
                LocalDateTime timeNow = LocalDateTime.now();
                LocalDateTime timeDateOfBookReceiving = LocalDateTime.of(receivedBookDto.getDateOfBookReceiving(), LocalTime.now());
                if (timeNow.getYear() != timeDateOfBookReceiving.getYear() ||
                        timeNow.getDayOfYear() - timeDateOfBookReceiving.getDayOfYear() >=
                                timeNow.getDayOfMonth()) {
                    receivedBooksDtoByLibraryCard.add(receivedBookDto);
                }
            }

            if (receivedBooksDtoByLibraryCard.isEmpty()) {
                continue;
            }

            send(
                    libraryCardDto.getClient().getEmail(),
                    "Message from library",
                    getMessage(libraryCardDto, receivedBooksDtoByLibraryCard));
        }
    }

    private String getMessage(LibraryCardDto libraryCardDto, List<ReceivedBookDto> receivedBooksDtoByLibraryCard) {
        StringBuilder message = new StringBuilder("Уважаемый " +
                libraryCardDto.getClient().getPassport().getName() + " " +
                libraryCardDto.getClient().getPassport().getSurname() +
                " Вы должны библиотеке следующие книжки: ");
        for (ReceivedBookDto receivedBookDto : receivedBooksDtoByLibraryCard) {
            message
                    .append(receivedBookDto.toString())
                    .append(" Дата взятия книжки: ")
                    .append(receivedBookDto.getDateOfBookReceiving())
                    .append(". Большая просьба, верните книжки");
        }
        return message.toString();
    }
}