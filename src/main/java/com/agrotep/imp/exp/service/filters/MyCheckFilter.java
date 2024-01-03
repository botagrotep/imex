package com.agrotep.imp.exp.service.filters;

import com.agrotep.imp.exp.dto.PersonDto;
import com.agrotep.imp.exp.dto.enums.TransportationFilterType;
import com.agrotep.imp.exp.entity.Transportation;
import com.agrotep.imp.exp.service.PersonService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.function.Predicate;

@Component
@RequiredArgsConstructor
public class MyCheckFilter implements ImportExportFilter {
    @Getter
    private final TransportationFilterType type = TransportationFilterType.MY_CHECK;

    private final PersonService personService;

    @Override
    public Predicate<Transportation> getPredicate() {
        String currentUserLogin = SecurityContextHolder.getContext().getAuthentication().getName();
        return (transportation) -> {

            String name = transportation.getManager().getName();
            PersonDto personDto = personService.findByName(name);
            return personDto != null && Objects.equals(personDto.getLogin(), currentUserLogin);
        };
    }
}
