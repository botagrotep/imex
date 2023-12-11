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

import static com.agrotep.imp.exp.dto.TransportationDetailsDto.DATE_FORMATTER;
import static com.agrotep.imp.exp.dto.TransportationDetailsDto.TIME_FORMATTER;

@Mapper(componentModel = "spring")
public abstract class TransportationConverter {
    @Mapping(target = "managerName", source = "t.manager.name")
    @Mapping(target = "managerId", source = "t.manager.id")
    public abstract TransportationDto toTransportationDto(Transportation t);
    public abstract List<TransportationDto> toTransportationDto(Collection<Transportation> t);
    public abstract TransportationDetailsDto toTransportationDetailsDto(Transportation t);


    public abstract Transportation toTransportation(TransportationDetailsDto transportationDetailsDto);
    public abstract Transportation toTransportation(TruckDto truckDto);

    @AfterMapping
    public void toStringFields(Transportation t, @MappingTarget TransportationDto dto) {
        setDirection(t, dto);
        setCondition(t, dto);
        setTransportationComment(t, dto);
        setSeverity(t, dto);

    }

    @AfterMapping
    public void toStringFields(Transportation t, @MappingTarget TransportationDetailsDto dto) {
        dto.setOrderDateStr(t.getOrderDate().format(DATE_FORMATTER));
        dto.setOrderTimeStr(t.getOrderTime().format(TIME_FORMATTER));
    }

    private static void setSeverity(Transportation t, TransportationDto dto) {
        String severity = StringUtils.hasText(t.getEquipage()) && StringUtils.hasText(t.getDriver())
                ? "green-light"
                : StringUtils.hasText(t.getTransportationComment()) ? "red-light"
                : "";
        dto.setSeverity(severity);
    }

    private static void setTransportationComment(Transportation t, TransportationDto dto) {
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

    private static void setCondition(Transportation t, TransportationDto dto) {
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
    }

    private static void setDirection(Transportation t, TransportationDto dto) {
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
    }
}
