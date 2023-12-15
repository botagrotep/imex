package com.agrotep.imp.exp.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TruckDto {
    private Long id;
    private String transportationComment;
    private String truckType;
    private String unloading;
    private String operationalStatusComment;
    private String status;
    private String ekmt;
    private String dangerousStatus;
    private Float distanceBetweenPointsKm;
    private Boolean isInCarPark;
    private String domesticCompany;
}
