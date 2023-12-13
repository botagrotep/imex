package com.agrotep.imp.exp.service.converter;


import com.agrotep.imp.exp.dto.LoadingDto;
import com.agrotep.imp.exp.dto.TransportationDto;
import com.agrotep.imp.exp.dto.TruckDto;
import com.agrotep.imp.exp.entity.Transportation;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class TruckDtoConverter {

    public abstract Transportation toTransportation(TruckDto truckDto);


}
