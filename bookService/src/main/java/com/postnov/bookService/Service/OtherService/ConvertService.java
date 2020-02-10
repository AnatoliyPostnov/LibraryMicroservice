package com.postnov.bookService.Service.OtherService;

import java.util.List;

public interface ConvertService<Dto, NotDto> {

    public List<Dto> convertToSetDto(List<NotDto> notDtos, Class<Dto> classDto);

    public List<NotDto> convertFromSetDto(List<Dto> dtos, Class<NotDto> classNotDto);

    public Dto convertToDto(NotDto notDto, Class<Dto> classDto);

    public NotDto convertFromDto(Dto dto, Class<NotDto> classNotDto);

}
