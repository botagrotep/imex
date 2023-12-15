package com.agrotep.imp.exp.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class TransportationDetailsDto {
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");
    public static final DateTimeFormatter TIME_FORMATTER_SHORT = DateTimeFormatter.ofPattern("HH:mm");

    private Long id;
    @NotEmpty(message = "ім'я менеджера не повинно бути порожнім")
    private String managerName;
    private Long managerId;
    private String loadingCity;
    private String unloadingCity;
    private String loadingCountry;
    private String unloadingCountry;
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
    private Integer temperatureMin;
    private Integer temperatureMax;
    private String coordinatorComment;

    private Integer loadingNo;
    @NotEmpty
    private String loadingDateStr;
    private String loadingTimeStr;
    private String loadingType;
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
    private String unloadingDateStr;
    private String unloadingTimeStr;
    private String unloadingType;
    private String unloadingSenderReceiverLegalEntity;
    private String unloadingCityPostalIndex;
    private String unloadingRegion;
    private String unloadingAddress;
    private Double unloadingLongitude;
    private Double unloadingLatitude;
    private Double unloadingFreight;
    private String unloadingCurrency;
    private Boolean isUnloadingPaymentInCash;
    private Boolean isSentToDr;
}
