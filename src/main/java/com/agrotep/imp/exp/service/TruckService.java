package com.agrotep.imp.exp.service;

import com.agrotep.imp.exp.dto.TruckDto;
import com.agrotep.imp.exp.entity.Transportation;
import com.agrotep.imp.exp.repository.TransportationRepository;
import com.agrotep.imp.exp.repository.TruckRepository;
import com.agrotep.imp.exp.service.converter.TruckDtoConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<TruckDto> findAllWithCalculateRadiusFrom(Long transportationId) {
        Transportation transportationGiven = transportationRepository.findById(transportationId)
                .filter(t -> t.getLoadingLongitude() != null)
                .filter(t -> t.getLoadingLatitude() != null)
                .orElse(null);
        if (transportationGiven == null) {
            return Collections.emptyList();
        }

        return transportationRepository.findAll().stream()
                .filter(t -> t.getUnloadingLongitude() != null)
                .filter(t -> t.getUnloadingLatitude() != null)
                .filter(t -> t.getTruck() != null)
                .map(transportation -> toTruckDtoWithDistance(transportation, transportationGiven))
                .collect(Collectors.toList());
    }

    private TruckDto toTruckDtoWithDistance(Transportation transportation, Transportation transportationGiven) {
        double dLatitude
                = transportation.getUnloadingLatitude() - transportationGiven.getLoadingLatitude();
        double dLongitude
                = transportation.getUnloadingLongitude() - transportationGiven.getLoadingLongitude();
        double averageLatitude = FACTOR_FOR_GRADUSES
                * (transportation.getUnloadingLatitude() + transportationGiven.getLoadingLatitude()) / 2;
        double distance = EARTH_RADIUS_KM * FACTOR_FOR_GRADUSES
                * Math.sqrt(Math.pow(dLatitude, 2) + Math.pow(Math.cos(averageLatitude) * dLongitude, 2));
        TruckDto truckDto = truckDtoConverter.toTruckDto(transportation.getTruck());
        truckDto.setDistanceBetweenPointsKm((int) distance);
        return truckDto;
    }
}
