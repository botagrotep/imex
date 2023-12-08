package com.agrotep.imp.exp.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
public class TransportationDetailsDto {
    private Long id;
    private String managerName;
    private Long managerId;
    private LocalDate orderDate;
    private LocalTime orderTime;
    private String clientName;
    private String clientContactPerson;
    private String cargoName;
    private Float cargoWeightTons;
    private Float cargoVolumeM3;
    private Integer cargoPlacesNumber;
    private String cargoPlacesType;
    private String cargoTruckType;
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
