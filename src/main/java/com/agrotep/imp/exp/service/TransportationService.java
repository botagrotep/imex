package com.agrotep.imp.exp.service;


import com.agrotep.imp.exp.dto.TransportationDetailsDto;
import com.agrotep.imp.exp.dto.TransportationDto;
import com.agrotep.imp.exp.dto.TransportationFilterType;
import com.agrotep.imp.exp.dto.TruckDto;
import com.agrotep.imp.exp.entity.Transportation;
import com.agrotep.imp.exp.repository.TransportationRepository;
import com.agrotep.imp.exp.repository.TruckRepository;
import com.agrotep.imp.exp.service.converter.TransportationDetailsDtoConverter;
import com.agrotep.imp.exp.service.converter.TransportationDtoConverter;
import com.agrotep.imp.exp.service.converter.TruckDtoConverter;
import com.agrotep.imp.exp.service.filters.ImportExportFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransportationService {
    public static final DateTimeFormatter DD_MM = DateTimeFormatter.ofPattern("dd.MM");
    private final TransportationRepository repository;
    private final TruckRepository truckRepository;

    private final TransportationDtoConverter converter;
    private final TransportationDetailsDtoConverter detailsDtoConverter;
    private final TruckDtoConverter truckDtoConverter;
    private final Map<TransportationFilterType, ImportExportFilter> filterMap;

    public SortedMap<String, List<TransportationDto>> findAll() {
        final Collection<Transportation> transportationsList = repository.findAll();
        return toMap(transportationsList);
    }

    public SortedMap<String, List<TransportationDto>> findAllFiltered(List<String> filters,
                                                                      LocalDate dateFrom, LocalDate dateTo, String borderCrossingPoint) {
        Set<TransportationFilterType> filtersEnums = new HashSet<>(TransportationFilterType.toFilters(filters));
        final Collection<Transportation> transportationsList = repository.findAll().stream()
                .filter(transportation -> applyFilters(transportation, dateFrom, dateTo, borderCrossingPoint,
                        filtersEnums))
                .toList();

        return toMap(transportationsList);
    }

    private boolean applyFilters(Transportation transportation, LocalDate dateFrom, LocalDate dateTo,
                                 String borderCrossingPoint, Set<TransportationFilterType> filtersEnums) {
        List<ImportExportFilter> andFilters = filtersEnums.stream()
                .filter(TransportationFilterType::isAnd)
                .map(filterMap::get)
                .filter(Objects::nonNull)
                .toList();
        List<ImportExportFilter> orFilters = filtersEnums.stream()
                .filter(f -> !f.isAnd())
                .map(filterMap::get)
                .filter(Objects::nonNull)
                .toList();
        boolean andVal = CollectionUtils.isEmpty(andFilters) || andFilters.stream()
                .map(ImportExportFilter::getPredicate)
                .allMatch(p -> p.test(transportation));

        if (dateFrom != null) {
            andVal = andVal && dateFrom.minusDays(1L).isBefore(transportation.getLoadingDate());
        }
        if (dateTo != null) {
            andVal = andVal && dateTo.plusDays(1L).isAfter(transportation.getLoadingDate());
        }
        if (StringUtils.hasText(borderCrossingPoint)) {
            andVal = andVal && borderCrossingPoint.equalsIgnoreCase(transportation.getBorderCrossingPoint());
        }

        boolean orVal = CollectionUtils.isEmpty(orFilters) || orFilters.stream()
                .map(ImportExportFilter::getPredicate)
                .anyMatch(p -> p.test(transportation));

        return andVal && orVal;
    }

    public TransportationDto saveOrCopy(TransportationDetailsDto transportationDetailsDto) {
        Transportation transportation = detailsDtoConverter.toTransportation(transportationDetailsDto);
        repository.findById(transportation.getId())
                .filter(t -> transportation.getLoadingDate() != null
                        && !transportation.getLoadingDate().equals(t.getLoadingDate()))
                .ifPresent(t -> {
                    transportation.setId(null);
                    t.setTruck(null);
                    t.setTransportationComment(String.format("перенесено на %s",
                            transportation.getLoadingDate().format(DD_MM)));
                    repository.save(t);
                });
        Transportation t = repository.save(transportation);
        return converter.toTransportationDto(t);
    }

    public TransportationDto cancel(TransportationDetailsDto transportationDetailsDto) {
        Transportation transportation = detailsDtoConverter.toTransportation(transportationDetailsDto);
        transportation.setTransportationComment("відміна");
        transportation.setTruck(null);
        return converter.toTransportationDto(repository.save(transportation));
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
        return TransportationRepository.COMPANIES;
    }

    public Collection<String> getCountries() {
        return TransportationRepository.COUNTRIES;
    }

    public Collection<String> getBorderCrossingPoints() {
        return TransportationRepository.BORDER_CROSSING_POINTS;
    }

    private TreeMap<String, List<TransportationDto>> toMap(Collection<Transportation> transportationsList) {
        List<TransportationDto> dtos = converter.toTransportationDto(transportationsList);
        Map<String, List<TransportationDto>> transportationsGroups
                = dtos.stream()
                .filter(t -> t.getTransportationDate() != null)
                .collect(Collectors.groupingBy(TransportationDto::getTransportationDate));
        return new TreeMap<>(transportationsGroups);
    }
}
