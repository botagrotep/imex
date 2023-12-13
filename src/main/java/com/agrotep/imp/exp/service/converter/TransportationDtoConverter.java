package com.agrotep.imp.exp.service.converter;


import com.agrotep.imp.exp.dto.LoadingDto;
import com.agrotep.imp.exp.dto.TransportationDto;
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
public abstract class TransportationDtoConverter {

    public static final String DEFAULT_COMMENT = "Додати коментар";

    @Mapping(target = "managerName", source = "t.manager.name")
    @Mapping(target = "managerId", source = "t.manager.id")
    public abstract TransportationDto toTransportationDto(Transportation t);
    public abstract List<TransportationDto> toTransportationDto(Collection<Transportation> t);

    @AfterMapping
    public void toStringFields(Transportation t, @MappingTarget TransportationDto dto) {
        dto.setTransportationDate(t.getLoadingDate());

        setDirection(t, dto);
        setCondition(t, dto);
        setTransportationComment(t, dto);
        setSeverity(t, dto);
        setDefaultCommentsForEmptyFields(dto);
    }

    private static void setDefaultCommentsForEmptyFields(TransportationDto dto) {
        if (!StringUtils.hasText(dto.getCoordinatorComment())) {
            dto.setCoordinatorComment(DEFAULT_COMMENT);
        }
        if (!StringUtils.hasText(dto.getComment())) {
            dto.setComment(DEFAULT_COMMENT);
        }
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
        transportationComment
                .append(t.getTransportationComment());
        if (!StringUtils.hasText(transportationComment)) {
            transportationComment.append(DEFAULT_COMMENT);
        }
        dto.setTransportationComment(transportationComment
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
        if (t.getIsCargoAdr() == Boolean.TRUE) {
            conditionBuilder.append("ADR");
        }

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
