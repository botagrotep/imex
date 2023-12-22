package com.agrotep.imp.exp.service;

import com.agrotep.imp.exp.dto.PersonDto;
import com.agrotep.imp.exp.repository.PersonRepository;
import com.agrotep.imp.exp.service.converter.PersonDtoConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepository repository;
    private final PersonDtoConverter converter;
    public PersonDto findByName(String name) {
        return repository.findByName(name).map(converter::toPersonDto).orElse(null);
    }
}
