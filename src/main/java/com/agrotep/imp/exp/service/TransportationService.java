package com.agrotep.imp.exp.service;


import com.agrotep.imp.exp.dto.*;
import com.agrotep.imp.exp.dto.enums.TransportationFilterType;
import com.agrotep.imp.exp.entity.Loading;
import com.agrotep.imp.exp.entity.Transportation;
import com.agrotep.imp.exp.repository.TransportationRepository;
import com.agrotep.imp.exp.repository.TruckRepository;
import com.agrotep.imp.exp.service.converter.EmptyTransportationDtoConverter;
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

import static com.agrotep.imp.exp.entity.enums.LoadingType.LOADING_TYPES;

@Service
@RequiredArgsConstructor
public class TransportationService {
    public static final DateTimeFormatter DD_MM = DateTimeFormatter.ofPattern("dd.MM");
    private final TransportationRepository repository;
    private final TruckRepository truckRepository;

    private final TransportationDtoConverter converter;
    private final TransportationDetailsDtoConverter detailsDtoConverter;
    private final TruckDtoConverter truckDtoConverter;
    private final EmptyTransportationDtoConverter emptyTransportationDtoConverter;

    private final Map<TransportationFilterType, ImportExportFilter> filterMap;

    public Map<String, List<TransportationDto>> findAll() {
        final Collection<Transportation> transportationsList = repository.findAll();
        return toMap(transportationsList);
    }

    public Map<String, List<TransportationDto>> findAllFiltered(List<String> filters,
                                                                      LocalDate dateFrom, LocalDate dateTo, String borderCrossingPoint) {
        Set<TransportationFilterType> filtersEnums = new HashSet<>(TransportationFilterType.toFilters(filters));
        final Collection<Transportation> transportationsList = repository.findAll().stream()
                .filter(transportation -> applyFilters(transportation, dateFrom, dateTo, borderCrossingPoint,
                        filtersEnums))
                .toList();

        return toMap(transportationsList, dateFrom, dateTo);
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
            LocalDate dayBeforeFrom = dateFrom.minusDays(1L);
            andVal = andVal && transportation.getLoadings().stream()
                    .map(Loading::getLoadingDate)
                    .filter(Objects::nonNull)
                    .allMatch(dayBeforeFrom::isBefore);
        }
        if (dateTo != null) {
            LocalDate dayAfterTo = dateTo.plusDays(1L);
            andVal = andVal && transportation.getLoadings().stream()
                    .map(Loading::getLoadingDate)
                    .filter(Objects::nonNull)
                    .allMatch(dayAfterTo::isAfter);
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
        LocalDate firstLoadingDate = getFirstLoadingDate(transportation);
        repository.findById(transportation.getId())
                .filter(t -> firstLoadingDate != null
                        && !firstLoadingDate.equals(getFirstLoadingDate(t)))
                .ifPresent(t -> {
                    transportation.setId(null);
                    t.setTruck(null);
                    t.setTransportationComment(String.format("перенесено на %s", firstLoadingDate.format(DD_MM)));
                    repository.save(t);
                });
        Transportation t = repository.save(transportation);
        return converter.toTransportationDto(t);
    }

    private static LocalDate getFirstLoadingDate(Transportation transportation) {
        return transportation.getLoadings().stream()
                .filter(l -> LOADING_TYPES.contains(l.getLoadingType()))
                .min(Comparator.comparing(Loading::getLoadingNo))
                .map(Loading::getLoadingDate)
                .orElse(null);
    }

    public void save(EmptyTransportationDto dto) {
        Transportation transportation = emptyTransportationDtoConverter.toTransportation(dto);
        repository.save(transportation);
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

    private Map<String, List<TransportationDto>> toMap(Collection<Transportation> transportationsList) {
        return this.toMap(transportationsList, null, null);
    }

    private Map<String, List<TransportationDto>> toMap(Collection<Transportation> transportationsList,
                                                       LocalDate dateFrom, LocalDate dateTo) {
        List<TransportationDto> dtos = converter.toTransportationDto(transportationsList);
        if (dateFrom != null && dateTo != null) {
            LocalDate startDate = dateFrom.isBefore(dateTo) ? dateFrom : dateTo;
            LocalDate endDate = (dateFrom.isBefore(dateTo) ? dateTo : dateFrom).plusDays(1L);
            Set<LocalDate> availableDates = dtos.stream()
                    .map(TransportationDto::getLoadingDate)
                    .collect(Collectors.toSet());
            List<TransportationDto> datesWoTransportations = startDate.datesUntil(endDate)
                    .filter(d -> !availableDates.contains(d))
                    .map(d -> {
                        TransportationDto dto = new TransportationDto();
                        dto.setLoadingDate(d);
                        return dto;
                    })
                    .toList();
            dtos.addAll(datesWoTransportations);
        }
        Map<String, List<TransportationDto>> transportationsGroups
                = dtos.stream()
                .filter(t -> t.getTransportationDateStr() != null)
                .collect(Collectors.groupingBy(TransportationDto::getTransportationDateStr));

        return transportationsGroups.entrySet().stream()
                .sorted(Comparator.comparing(o -> Objects.requireNonNull(CollectionUtils.firstElement(o.getValue()))
                        .getLoadingDate()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> b, LinkedHashMap::new));    }
}
