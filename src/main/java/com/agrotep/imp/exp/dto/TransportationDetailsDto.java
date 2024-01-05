package com.agrotep.imp.exp.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
@Setter
public class TransportationDetailsDto {
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");
    public static final DateTimeFormatter TIME_FORMATTER_SHORT = DateTimeFormatter.ofPattern("HH:mm");

    private Long id;
//    @NotEmpty(message = "ім'я менеджера не повинно бути порожнім")
    private String managerName;
    private Long managerId;
    private List<LoadingDto> loadings;
    private String borderCrossingPoint;
    private String orderDateStr;
    private String orderTimeStr;
    private String clientCompany;
    private String clientCompanyFromSelector;
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

    private String loadingSenderReceiverLegalEntity;
    private Double freight;
    private String currency;
    private Boolean isPaymentInCash;

    private Boolean isSentToDr;
    private Boolean isPrinted;
    private String comment;
    private String customerApplicationNo;
    private String customerApplicationDateStr;
}
