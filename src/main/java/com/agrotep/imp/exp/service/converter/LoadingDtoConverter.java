package com.agrotep.imp.exp.service.converter;

import com.agrotep.imp.exp.dto.LoadingDto;
import com.agrotep.imp.exp.entity.Loading;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import static com.agrotep.imp.exp.dto.TransportationDetailsDto.DATE_FORMATTER;
import static com.agrotep.imp.exp.dto.TransportationDetailsDto.TIME_FORMATTER;

@Mapper(componentModel = "spring")
public abstract class LoadingDtoConverter {
    public abstract LoadingDto toLoadingDto(Loading loading);

    @AfterMapping
    public void toStringFields(Loading loading, @MappingTarget LoadingDto dto) {
        dto.setLoadingDate(loading.getLoadingDate().format(DATE_FORMATTER));
        dto.setLoadingTime(loading.getLoadingTime().format(TIME_FORMATTER));
    }

}
