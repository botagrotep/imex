package com.agrotep.imp.exp.service.converter;


import com.agrotep.imp.exp.dto.TransportationDto;
import com.agrotep.imp.exp.entity.Transportation;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.util.StringUtils;

import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

@Mapper(componentModel = "spring")
public abstract class TransportationDtoConverter {

    public static final String DEFAULT_COMMENT = "+ коментар";

    @Mapping(target = "managerName", source = "t.manager.name")
    @Mapping(target = "managerId", source = "t.manager.id")
    @Mapping(target = "equipage", source = "t.truck.equipage")
    @Mapping(target = "driver", source = "t.truck.driver")
    @Mapping(target = "truckId", source = "t.truck.id")
    public abstract TransportationDto toTransportationDto(Transportation t);

    public abstract List<TransportationDto> toTransportationDto(Collection<Transportation> t);

    @AfterMapping
    public void toStringFields(Transportation t, @MappingTarget TransportationDto dto) {
        if (t.getLoadingDate() != null) {
            dto.setTransportationDate(t.getLoadingDate().format(DateTimeFormatter.ofPattern("E dd MMMM yyyy")));
        }

        setDirection(t, dto);
        setCondition(t, dto);
        setSeverity(t, dto);
    }

    private static void setSeverity(Transportation t, TransportationDto dto) {
        String severity = t.isSentToDr() ? "btn-info"
                : StringUtils.hasText(dto.getEquipage()) && StringUtils.hasText(dto.getDriver())
                ? "btn-success"
                : StringUtils.hasText(t.getTransportationComment()) ? "btn-danger"
                : "btn-light";
        dto.setSeverity(severity);
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
        if (t.getIsCargoAdr() == Boolean.TRUE) {
            conditionBuilder.append("ADR");
        }

        dto.setConditions(conditionBuilder.toString());
    }

    private static void setDirection(Transportation t, TransportationDto dto) {
        StringBuilder s = new StringBuilder(t.getLoadingCity());

        if (StringUtils.hasText(t.getLoadingCountry())) {
            if (StringUtils.hasText(s)) {
                s.append(", ");
            }
            s.append(t.getLoadingCountry()).append(" - ");
        }
        if (StringUtils.hasText(t.getBorderCrossingPoint())) {
            s.append(t.getBorderCrossingPoint()).append(" - ");
        }
        if (StringUtils.hasText(t.getUnloadingCity())) {
            s.append(t.getUnloadingCity()).append(", ");
        }
        if (StringUtils.hasText(t.getUnloadingCountry())) {
            s.append(t.getUnloadingCountry());
        }
        dto.setDirection(s.toString());
    }

}
