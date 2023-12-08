package com.agrotep.imp.exp.entity;


//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
import com.agrotep.imp.exp.dto.LoadingDto;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

//@Entity // This tells Hibernate to make a table out of this class
@Getter
@Setter
@Builder(toBuilder = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Transportation {
//    @Id
//    @GeneratedValue(strategy= GenerationType.AUTO)
    @EqualsAndHashCode.Include
    private Long id;
    private LocalDate transportationDate;
    @Builder.Default
    private String loadingCity = "";
    @Builder.Default
    private String loadingCountry = "";
    @Builder.Default
    private String borderCrossingPoint = "";
    @Builder.Default
    private String unloadingCity = "";
    @Builder.Default
    private String unloadingCountry = "";
    @Builder.Default
    private String typeOfTruck = "";
    @Builder.Default
    private String clientCompany = "";
    private Person manager;
    @Builder.Default
    private String cargo = "";
    private Integer temperatureMin;
    private Integer temperatureMax;
    @Builder.Default
    private String dangerousStatus = "";
    @Builder.Default
    private String coordinatorComment = "";
    @Builder.Default
    private String transportationComment = "";
    @Builder.Default
    private String equipage = "";
    @Builder.Default
    private String driver = "";
    @Builder.Default
    private String comment = "";

    @Builder.Default
    private LocalDate orderDate = LocalDate.now();
    @Builder.Default
    private LocalTime orderTime = LocalTime.now();
    @Builder.Default
    private String clientName = "";
    @Builder.Default
    private String clientContactPerson = "";
    @Builder.Default
    private String cargoName = "";
    private Float cargoWeightTons;
    private Float cargoVolumeM3;
    private Integer cargoPlacesNumber;
    @Builder.Default
    private String cargoPlacesType = "";
    @Builder.Default
    private String cargoTruckType = "";
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
