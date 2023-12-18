package com.agrotep.imp.exp.service.converter;


import com.agrotep.imp.exp.dto.TransportationDetailsDto;
import com.agrotep.imp.exp.entity.Person;
import com.agrotep.imp.exp.entity.Transportation;
import com.agrotep.imp.exp.repository.PersonRepository;
import com.agrotep.imp.exp.repository.TransportationRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static com.agrotep.imp.exp.dto.TransportationDetailsDto.*;

@Mapper(componentModel = "spring")
public abstract class TransportationDetailsDtoConverter {

    @Autowired
    protected TransportationRepository repository;
    @Autowired
    protected PersonRepository personRepository;

    @Mapping(target = "freight", source = "t.loadingFreight")
    @Mapping(target = "currency", source = "t.loadingCurrency")
    public abstract TransportationDetailsDto toTransportationDetailsDto(Transportation t);


    @Mapping(target = "loadingFreight", source = "transportationDetailsDto.freight")
    @Mapping(target = "loadingCurrency", source = "transportationDetailsDto.currency")
    public abstract Transportation toTransportation(TransportationDetailsDto transportationDetailsDto);

    @AfterMapping
    public void toStringFields(Transportation t, @MappingTarget TransportationDetailsDto dto) {
        setDateStr(t::getOrderDate, dto::setOrderDateStr);
        setTimeStr(t::getOrderTime, dto::setOrderTimeStr);
        setDateStr(t::getLoadingDate, dto::setLoadingDateStr);
        setTimeStr(t::getLoadingTime, dto::setLoadingTimeStr);
        setDateStr(t::getUnloadingDate, dto::setUnloadingDateStr);
        setTimeStr(t::getUnloadingTime, dto::setUnloadingTimeStr);

        Person manager = t.getManager();
        if (manager != null) {
            dto.setManagerId(manager.getId());
            dto.setManagerName(manager.getName());
        }
        dto.setIsCargoAdr(t.getIsCargoAdr());
    }

    // IS NOT WORKING
    @BeforeMapping
    protected void enrichWithPersisted(TransportationDetailsDto dto, @MappingTarget Transportation t) {
        Long id = dto.getId();
        if (id != null) {
            repository.findById(id)
                    .ifPresent(tR -> t.setManager(tR.getManager()));
        }
    }

    @AfterMapping
    protected void parseStringFields(TransportationDetailsDto dto, @MappingTarget Transportation t) {
        setDate(dto::getOrderDateStr, t::setOrderDate);
        setTime(dto::getOrderTimeStr, t::setOrderTime);
        setDate(dto::getLoadingDateStr, t::setLoadingDate);
        setTime(dto::getLoadingTimeStr, t::setLoadingTime);
        setDate(dto::getUnloadingDateStr, t::setUnloadingDate);
        setTime(dto::getUnloadingTimeStr, t::setUnloadingTime);

        enrichWithPersistedData(dto, t);

        if (StringUtils.hasText(dto.getClientCompanyFromSelector())) {
            t.setClientCompany(dto.getClientCompanyFromSelector());
        }
    }

    private void enrichWithPersistedData(TransportationDetailsDto dto, Transportation t) {
        Long id = dto.getId();
        if (id != null) {
            repository.findById(id)
                    .ifPresent(tR -> {
                        t.setManager(tR.getManager());
                        t.setTransportationComment(tR.getTransportationComment());
                        t.setTruck(tR.getTruck());
                    });
        } else if (StringUtils.hasText(dto.getManagerName())) {
            personRepository.findByName(dto.getManagerName())
                    .ifPresent(t::setManager);
        }
    }

    void setDateStr(Supplier<LocalDate> getter, Consumer<String> setter) {
        LocalDate t = getter.get();
        if (t != null) {
            setter.accept(t.format(DATE_FORMATTER));
        }
    }

    void setTimeStr(Supplier<LocalTime> getter, Consumer<String> setter) {
        LocalTime t = getter.get();
        if (t != null) {
            setter.accept(t.format(TIME_FORMATTER));
        }
    }

    void setDate(Supplier<String> getter, Consumer<LocalDate> setter) {
        String t = getter.get();
        if (StringUtils.hasText(t)) {
            setter.accept(LocalDate.parse(t, DATE_FORMATTER));
        }
    }

    void setTime(Supplier<String> getter, Consumer<LocalTime> setter) {
        String t = getter.get();
        if (StringUtils.hasText(t)) {
            DateTimeFormatter timeFormatter = StringUtils.countOccurrencesOf(t, ":") == 1
                    ? TIME_FORMATTER_SHORT
                    : TIME_FORMATTER;
            setter.accept(LocalTime.parse(t, timeFormatter));
        }
    }
}
