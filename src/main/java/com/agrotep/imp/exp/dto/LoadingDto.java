package com.agrotep.imp.exp.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Builder
public class LoadingDto {
    private Integer no;
    private LocalDate loadingDate;
    private LocalTime loadingTime;
    private String loadingType;
    private String senderReceiverLegalEntity;
    private String loadingCity;
    private String loadingCityPostalIndex;
    private String loadingCountry;
    private String loadingRegion;
    private String loadingAddress;
    private Double loadingLongitude;
    private Double loadingLatitude;
    private Double freight;
    private String currency;
    private Boolean isCash;
}
