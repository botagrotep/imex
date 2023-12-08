package com.agrotep.imp.exp.service.converter;


import com.agrotep.imp.exp.dto.TransportationDetailsDto;
import com.agrotep.imp.exp.dto.TransportationDto;
import com.agrotep.imp.exp.dto.TruckDto;
import com.agrotep.imp.exp.entity.Transportation;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class TransportationConverter {
    @Mapping(target = "managerName", source = "t.manager.name")
    @Mapping(target = "managerId", source = "t.manager.id")
    public abstract TransportationDto toTransportationDto(Transportation t);
    public abstract List<TransportationDto> toTransportationDto(Collection<Transportation> t);


    public abstract Transportation toTransportation(TransportationDetailsDto transportationDetailsDto);
    public abstract Transportation toTransportation(TruckDto truckDto);

    @AfterMapping
    public void toStringFields(Transportation t, @MappingTarget TransportationDto dto) {
        StringBuilder s = new StringBuilder(t.getLoadingCity());
        if (StringUtils.hasText(t.getLoadingCountry())) {
            s.append(", ").append(t.getLoadingCountry()).append(" - ");
        }
        if (StringUtils.hasText(t.getBorderCrossingPoint())) {
            s.append(t.getBorderCrossingPoint()).append(" - ");
        }
        s.append(t.getUnloadingCity());
        if (StringUtils.hasText(t.getUnloadingCountry())) {
            s.append(", ").append(t.getUnloadingCountry());
        }
        dto.setDirection(s.toString());

        StringBuilder conditionBuilder = new StringBuilder();
        if (t.getTemperatureMin() != null) {
            if (t.getTemperatureMin() > 0) {
                conditionBuilder.append("+");
            }
            conditionBuilder.append(t.getTemperatureMin()).append(" ");
        }
        if (t.getTemperatureMax() != null) {
            if (t.getTemperatureMax() > 0) {
                conditionBuilder.append("+");
            }
            conditionBuilder.append(t.getTemperatureMax()).append(" ");
        }
        conditionBuilder.append(t.getDangerousStatus());

        dto.setConditions(conditionBuilder.toString());
        
        StringBuilder transportationComment = new StringBuilder();
        if (StringUtils.hasText(t.getEquipage())) {
            transportationComment.append(t.getEquipage()).append(" ");
        }
        if (StringUtils.hasText(t.getDriver())) {
            transportationComment.append(t.getDriver()).append(" ");
        }
        dto.setTransportationComment(transportationComment
                .append(t.getTransportationComment())
                .toString()
                .trim());
    }
}
