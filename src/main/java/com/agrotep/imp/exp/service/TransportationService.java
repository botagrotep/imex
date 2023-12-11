package com.agrotep.imp.exp.service;


import com.agrotep.imp.exp.dto.TransportationDetailsDto;
import com.agrotep.imp.exp.dto.TransportationDto;
import com.agrotep.imp.exp.dto.TruckDto;
import com.agrotep.imp.exp.entity.Transportation;
import com.agrotep.imp.exp.repository.TransportationRepository;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import com.agrotep.imp.exp.service.converter.TransportationConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransportationService {
    private final TransportationRepository repository;
    private final TransportationConverter converter;
    
    public SortedMap<LocalDate, List<TransportationDto>> findAll() {
        final Collection<Transportation> transportationsList = repository.findAll();
        List<TransportationDto> dtos = converter.toTransportationDto(transportationsList);
        Map<LocalDate, List<TransportationDto>> transportationsGroups
            = dtos.stream().collect(Collectors.groupingBy(TransportationDto::getTransportationDate));
        return new TreeMap<>(transportationsGroups);
    }

    public TransportationDto save(TransportationDetailsDto transportationDetailsDto) {
        Transportation transportation = converter.toTransportation(transportationDetailsDto);
        Transportation t = repository.save(transportation);
        return converter.toTransportationDto(t);
    }

    public Transportation saveTruck(TruckDto t) {
        Transportation transportation = converter.toTransportation(t);
        return repository.save(transportation);
    }

    public Optional<TransportationDetailsDto> findTransportationDetailsById(Long id) {
        return repository.findById(id)
                .map(converter::toTransportationDetailsDto);
    }
}
