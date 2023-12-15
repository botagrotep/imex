package com.agrotep.imp.exp.service;

import com.agrotep.imp.exp.dto.TruckDto;
import com.agrotep.imp.exp.repository.TruckRepository;
import com.agrotep.imp.exp.service.converter.TruckDtoConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TruckService {
    private final TruckRepository repository;
    private final TruckDtoConverter truckDtoConverter;



    public List<TruckDto> findAllInRadius() {
        return repository.findAll().stream()
                .map(truckDtoConverter::toTruckDto)
                .collect(Collectors.toList());
    }
}
