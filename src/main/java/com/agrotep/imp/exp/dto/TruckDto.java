package com.agrotep.imp.exp.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TruckDto {
    private Long id;
    private String transportationComment;
    private String truckType;
    private LocalDate loadingDate;
    private String loadingCity;
    private String loadingCountry;
    private String operationalStatusComment;
    private String status;
    private String ekmt;
    private String dangerousStatus = "";
    private Float distanceBetweenPointsKm;
    private Boolean isInCarPark;
    private String domesticCompany;
}
