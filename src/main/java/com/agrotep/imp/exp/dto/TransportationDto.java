package com.agrotep.imp.exp.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class TransportationDto {

    private Long id;
    private LocalDate transportationDate;
    private LocalDate transportationFillingDate;
    private String direction;
    private String typeOfTruck;
    private String clientCompany;
    private String managerName;
    private Long managerId;
    private String cargo;
    private String conditions;
    private String coordinatorComment;
    private String transportationComment;
    private String equipage = "";
    private String driver = "";
    private String comment;
    private String severity;
    private LocalDate loadingDate;
    private LocalTime loadingTime;
    private Boolean isSentToDr;
    private String loadingCity;
    private String loadingCountry;
    private String borderCrossingPoint;
    private String unloadingCity;
    private String unloadingCountry;
    private Long truckId;
    }
