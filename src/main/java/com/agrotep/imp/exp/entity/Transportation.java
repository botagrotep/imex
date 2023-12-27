package com.agrotep.imp.exp.entity;


//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

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
    private Person manager;
    private LocalDate transportationFillingDate;
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
    @Builder.Default
    private String cargo = "";
    private Integer temperatureMin;
    private Integer temperatureMax;
    @Builder.Default
    private String coordinatorComment = "";
    @Builder.Default
    private String transportationComment = "";
    @Builder.Default
    private String comment = "";
    private  Truck truck;

    @Builder.Default
    private LocalDate orderDate = LocalDate.now();
    @Builder.Default
    private LocalTime orderTime = LocalTime.now();
    @Builder.Default
    private String clientContactPerson = "";
    private Float cargoWeightTons;
    private Float cargoVolumeM3;
    private Integer cargoPlacesNumber;
    @Builder.Default
    private String cargoPlacesType = "";
    private Boolean isCargoTir;
    private Boolean isCargoAdr;
    private Boolean isCargoAdr1;
    private Boolean isCargoCustomsServicesPaid;
    private Boolean isCargoStickers;
    private Boolean isCargoThermalPrinterNeeded;

    private Integer loadingNo;
    private LocalDate loadingDate;
    private LocalTime loadingTime;
    private String loadingSenderReceiverLegalEntity;
    private String loadingCityPostalIndex;
    private String loadingRegion;
    private String loadingAddress;
    private Double loadingLongitude;
    private Double loadingLatitude;
    private Double loadingFreight;
    private String loadingCurrency;
    private Boolean isLoadingPaymentInCash;

    private Integer unloadingNo;
    private LocalDate unloadingDate;
    private LocalTime unloadingTime;
    private String unloadingSenderReceiverLegalEntity;
    private String unloadingCityPostalIndex;
    private String unloadingRegion;
    private String unloadingAddress;
    private Double unloadingLongitude;
    private Double unloadingLatitude;
    private Double unloadingFreight;
    private String unloadingCurrency;
    private Boolean isUnloadingPaymentInCash;
    private boolean isSentToDr;
    private String customerApplicationNo;
    private LocalDate customerApplicationDate;
}
