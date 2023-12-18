package com.agrotep.imp.exp.service;


import com.agrotep.imp.exp.dto.TransportationDetailsDto;
import com.agrotep.imp.exp.dto.TransportationDto;
import com.agrotep.imp.exp.dto.TruckDto;
import com.agrotep.imp.exp.entity.Transportation;
import com.agrotep.imp.exp.repository.TransportationRepository;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import com.agrotep.imp.exp.repository.TruckRepository;
import com.agrotep.imp.exp.service.converter.TransportationDetailsDtoConverter;
import com.agrotep.imp.exp.service.converter.TransportationDtoConverter;
import com.agrotep.imp.exp.service.converter.TruckDtoConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransportationService {
    private final TransportationRepository repository;
    private final TruckRepository truckRepository;

    private final TransportationDtoConverter converter;
    private final TransportationDetailsDtoConverter detailsDtoConverter;
    private final TruckDtoConverter truckDtoConverter;

    public SortedMap<LocalDate, List<TransportationDto>> findAll() {
        final Collection<Transportation> transportationsList = repository.findAll();
        List<TransportationDto> dtos = converter.toTransportationDto(transportationsList);
        Map<LocalDate, List<TransportationDto>> transportationsGroups
                = dtos.stream()
                .filter(t -> t.getTransportationDate() != null)
                .collect(Collectors.groupingBy(TransportationDto::getTransportationDate));
        return new TreeMap<>(transportationsGroups);
    }

    public TransportationDto save(TransportationDetailsDto transportationDetailsDto) {
        Transportation transportation = detailsDtoConverter.toTransportation(transportationDetailsDto);
        Transportation t = repository.save(transportation);
        return converter.toTransportationDto(t);
    }

    public Transportation saveTruck(TruckDto t) {
        Transportation transportation = truckDtoConverter.toTransportation(t);
        return repository.save(transportation);
    }

    public Optional<TransportationDetailsDto> findTransportationDetailsById(Long id) {
        return repository.findById(id)
                .map(detailsDtoConverter::toTransportationDetailsDto);
    }

    public Optional<TransportationDto> findTransportationById(Long id) {
        return repository.findById(id)
                .map(converter::toTransportationDto);
    }

    public void setTruck(Long truckId, Long transportationId) {
        truckRepository.findById(truckId)
                .ifPresent(t -> repository.findById(transportationId)
                        .ifPresent(transportation -> {
                            transportation.setTruck(t);
                            repository.save(transportation);
                        }));
    }

    public Collection<String> getCompanies() {
        Set<String> predefinedCompanies = new HashSet<>(Set.of(
                "Fererro (оплата 45 календарних днів по виставленню інвойсу, ліміт 300000 грн)",
                "МХП (по факту вивантаження, ліміт 50000 грн)",
                "Веселі бобри (10 днів по вивантаженню, ліміт 40000 грн)"
        ));
        predefinedCompanies.addAll(this.findAll().values().stream()
                .flatMap(Collection::stream)
                .map(TransportationDto::getClientCompany)
                .toList());
        return predefinedCompanies;
    }

    public Collection<String> getCountries() {
        Set<String> countries = new HashSet<>(Set.of("UA", "FR", "DE"));
        countries.addAll(repository.findAll().stream()
                .map(Transportation::getLoadingCountry)
                .filter(Objects::nonNull)
                .toList());
        countries.addAll(repository.findAll().stream()
                .map(Transportation::getUnloadingCountry)
                .filter(Objects::nonNull)
                .toList());
        return countries;
    }
}
