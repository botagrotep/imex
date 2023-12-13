package com.agrotep.imp.exp.service.converter;

import com.agrotep.imp.exp.dto.LoadingDto;
import org.mapstruct.Mapper;

//@Mapper(componentModel = "spring")

public interface LoadingDtoConverter {
    LoadingDto toLoadingDto();
}
