package com.postnov.bookService.Service.OtherService.Impl;

import com.postnov.bookService.Service.OtherService.ConvertService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConvertServiceImpl<Dto, NotDto> implements ConvertService<Dto, NotDto> {

    private final ModelMapper modelMapper;

    public ConvertServiceImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Dto> convertToSetDto(List<NotDto> notDtos, Class<Dto> classDto) {
        List<Dto> dto = new ArrayList<>();
        for (NotDto notDto : notDtos) {
            dto.add(convertToDto(notDto, classDto));
        }
        return dto;
    }

    @Override
    public List<NotDto> convertFromSetDto(List<Dto> dtos, Class<NotDto> classNotDto) {
        List<NotDto> notDtos = new ArrayList<>();
        for (Dto dto : dtos) {
            notDtos.add(convertFromDto(dto, classNotDto));
        }
        return notDtos;
    }

    @Override
    public Dto convertToDto(NotDto notDto, Class<Dto> classDto) {
        return modelMapper.map(notDto, classDto);
    }

    @Override
    public NotDto convertFromDto(Dto dto, Class<NotDto> classNotDto) {
        return modelMapper.map(dto, classNotDto);
    }
}
