package com.agrotep.imp.exp.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
@Setter
public class TransportationDetailsDto {
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    private Long id;
    private String managerName = "";
    private Long managerId;
    private String orderDateStr;
    private String orderTimeStr;
    private String clientCompany;
    private String clientContactPerson;
    private String cargo;
    private Float cargoWeightTons;
    private Float cargoVolumeM3;
    private Integer cargoPlacesNumber;
    private String cargoPlacesType;
    private String typeOfTruck;
    private Boolean isCargoTir;
    private Boolean isCargoAdr;
    private Boolean isCargoAdr1;
    private Boolean isCargoCustomsServicesPaid;
    private Boolean isCargoStickers;
    private Boolean isCargoThermalPrinterNeeded;
    private Integer cargoTemperatureFrom;
    private Integer cargoTemperatureTo;
    private List<LoadingDto> loadings;
}
