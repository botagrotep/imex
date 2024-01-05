package com.agrotep.imp.exp.service.converter;


import com.agrotep.imp.exp.dto.TruckDto;
import com.agrotep.imp.exp.entity.Loading;
import com.agrotep.imp.exp.entity.Transportation;
import com.agrotep.imp.exp.entity.Truck;
import com.agrotep.imp.exp.repository.TransportationRepository;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.agrotep.imp.exp.dto.TransportationDetailsDto.DATE_FORMATTER;

@Mapper(componentModel = "spring")
public abstract class TruckDtoConverter {
    @Autowired
    protected TransportationRepository transportationRepository;

    public abstract Transportation toTransportation(TruckDto truckDto);
    public abstract TruckDto toTruckDto(Truck t);

    @AfterMapping
    public void toStringFields(Truck truck, @MappingTarget TruckDto dto) {
        StringBuilder transportationComment = new StringBuilder();

        if (truck != null) {
            String equipage = truck.getEquipage();
            if (StringUtils.hasText(equipage)) {
                transportationComment.append(equipage).append(" ");
            }
            String driver = truck.getDriver();
            if (StringUtils.hasText(driver)) {
                transportationComment.append(driver).append(" ");
            }
        }

        transportationRepository.findTransportationForTruck(truck)
                        .ifPresent(t -> transportationComment.append(t.getTransportationComment()));

        dto.setTransportationComment(transportationComment
                .toString()
                .trim());

        transportationRepository.findTransportationForTruck(truck)
                .ifPresent(t -> setUnloading(dto, t));

        assert truck != null;
        LocalDateTime operationalStatusCommentDateTime = truck.getOperationalStatusCommentDateTime();
        if (operationalStatusCommentDateTime != null) {
        dto.setOperationalStatusCommentDateTimeStr(operationalStatusCommentDateTime
                .format(DateTimeFormatter.ofPattern("dd.MM, HH:mm")));
        }

    }

    private static void setUnloading(TruckDto dto, Transportation transportation) {
        List<Loading> loadings = transportation.getLoadings();
        if (CollectionUtils.isEmpty(loadings)) {
            return;
        }
        Loading loadingsLast = CollectionUtils.lastElement(loadings);
        StringBuilder unloading = new StringBuilder();
        assert loadingsLast != null;
        if (loadingsLast.getLoadingDate() != null) {
            unloading.append(loadingsLast.getLoadingDate().format(DATE_FORMATTER));
        }
        unloading.append(" ");
        if (StringUtils.hasText(loadingsLast.getLoadingCity())) {
            unloading.append(loadingsLast.getLoadingCity());
        }
        unloading.append(" ");
        if (StringUtils.hasText(loadingsLast.getLoadingCountry())) {
            unloading.append(loadingsLast.getLoadingCountry());
        }
        dto.setUnloading(unloading.toString().trim());
    }
}
