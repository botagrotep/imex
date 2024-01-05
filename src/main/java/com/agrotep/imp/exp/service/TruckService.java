package com.agrotep.imp.exp.service;

import com.agrotep.imp.exp.dto.TruckDto;
import com.agrotep.imp.exp.entity.Loading;
import com.agrotep.imp.exp.entity.Transportation;
import com.agrotep.imp.exp.entity.Truck;
import com.agrotep.imp.exp.repository.TransportationRepository;
import com.agrotep.imp.exp.repository.TruckRepository;
import com.agrotep.imp.exp.service.converter.TruckDtoConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.agrotep.imp.exp.entity.enums.LoadingType.LOADING_TYPES;
import static com.agrotep.imp.exp.entity.enums.LoadingType.UNLOADING_TYPES;

@Service
@RequiredArgsConstructor
public class TruckService {
    private final TruckRepository repository;
    private final TruckDtoConverter truckDtoConverter;
    private final TransportationRepository transportationRepository;
    private static final double EARTH_RADIUS_KM = 6_371.009;
    private static final double FACTOR_FOR_GRADUSES = Math.PI / 180;

    public TruckDto findById(Long id) {
        return repository.findById(id)
                .map(truckDtoConverter::toTruckDto)
                .orElse(null);
    }

    public List<TruckDto> findAll() {
        return repository.findAll().stream()
                .filter(t -> StringUtils.hasText(t.getEquipage()))
                .filter(t -> StringUtils.hasText(t.getDriver()))
                .filter(t -> StringUtils.hasText(t.getDomesticCompany()))
                .map(truckDtoConverter::toTruckDto)
                .toList();
    }
    public List<TruckDto> findAllWithCalculateRadiusFrom(Long transportationId) {
        Transportation transportationGiven = transportationRepository.findById(transportationId)
                .orElse(null);
        if (transportationGiven == null) {
            return Collections.emptyList();
        }

        return transportationRepository.findAll().stream()
                .filter(t -> t.getTruck() != null)
                .map(transportation -> toTruckDtoWithDistance(transportation, transportationGiven))
                .collect(Collectors.toList());
    }

    private TruckDto toTruckDtoWithDistance(Transportation transportation, Transportation transportationGiven) {
        double distance = getDistanceBetweenLoadingAndUnloading(transportation, transportationGiven);
        Truck truck = transportation.getTruck();
        TruckDto truckDto = truckDtoConverter.toTruckDto(truck);
        truckDto.setDistanceBetweenPointsKm((int) distance);
        List<Transportation> transportationsOfTruck = transportationRepository
                .findByTruckAndLoadingDateAfterEqual(truck, Objects.requireNonNull(CollectionUtils.firstElement(transportation.getLoadings())).getLoadingDate());
        boolean isNextLoadingPresent = transportationsOfTruck.stream().anyMatch(t -> !transportation.equals(t));
        truckDto.setIsNextLoadingPresent(isNextLoadingPresent);
        return truckDto;
    }

    private static double getDistanceBetweenLoadingAndUnloading(Transportation transportation,
                                                                Transportation transportationGiven) {
        Loading lastUnloading = transportation.getLoadings().stream()
                .filter(l -> UNLOADING_TYPES.contains(l.getLoadingType()))
                .filter(l -> l.getLoadingDate() != null)
                .max(Comparator.comparing(Loading::getLoadingDate))
                .orElse(null);
        if (lastUnloading == null) {
            return -1;
        }
        Loading firstLoading = transportationGiven.getLoadings().stream()
                .filter(l -> LOADING_TYPES.contains(l.getLoadingType()))
                .filter(l -> l.getLoadingDate() != null)
                .min(Comparator.comparing(Loading::getLoadingDate))
                .orElse(null);
        if (firstLoading == null) {
            return -1;
        }
        double dLatitude
                = lastUnloading.getLoadingLatitude() - firstLoading.getLoadingLatitude();
        double dLongitude
                = lastUnloading.getLoadingLongitude() - firstLoading.getLoadingLongitude();
        double averageLatitude = FACTOR_FOR_GRADUSES
                * (lastUnloading.getLoadingLatitude() + firstLoading.getLoadingLatitude()) / 2;
        return EARTH_RADIUS_KM * FACTOR_FOR_GRADUSES
                * Math.sqrt(Math.pow(dLatitude, 2) + Math.pow(Math.cos(averageLatitude) * dLongitude, 2));
    }
}
