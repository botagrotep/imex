package com.agrotep.imp.exp.service.converter;

import com.agrotep.imp.exp.dto.PersonDto;
import com.agrotep.imp.exp.entity.Person;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface PersonDtoConverter {
    PersonDto toPersonDto(Person p);
}
