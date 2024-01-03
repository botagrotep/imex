package com.agrotep.imp.exp.entity;


//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.logging.Formatter;

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
    private List<Loading> loadings;
    @Builder.Default
    private String borderCrossingPoint = "";
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
    private String loadingSenderReceiverLegalEntity;

    private Boolean isLoadingPaymentInCash;

    private boolean isSentToDr;
    private String customerApplicationNo;
    private LocalDate customerApplicationDate;
    private Double freight;
    private String currency;
}
