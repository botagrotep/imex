package com.agrotep.imp.exp.service.converter;

import com.agrotep.imp.exp.dto.EmptyTransportationDto;
import com.agrotep.imp.exp.entity.Transportation;
import com.agrotep.imp.exp.repository.TruckRepository;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

@Mapper(componentModel = "spring")
public abstract class EmptyTransportationDtoConverter {
    @Autowired
    protected TruckRepository truckRepository;
    public abstract Transportation toTransportation(EmptyTransportationDto e);

    @AfterMapping
    protected void parseStringFields(EmptyTransportationDto dto, @MappingTarget Transportation transportation) {
        if (StringUtils.hasLength(dto.getTruckId())) {
            truckRepository.findById(Long.parseLong(dto.getTruckId()))
                    .ifPresent(transportation::setTruck);
        }
    }
}
