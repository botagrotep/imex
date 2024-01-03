package com.agrotep.imp.exp.dto;

import com.agrotep.imp.exp.entity.enums.LoadingType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Builder
public class LoadingDto {
    private Long id;
    private String loadingCity;
    private String loadingCountry;

    private Integer loadingNo;
    private String loadingDate;
    private String loadingTime;
    private String loadingCityPostalIndex;
    private String loadingRegion;
    private String loadingAddress;
    private Double loadingLongitude;
    private Double loadingLatitude;
    private LoadingType loadingType;
}
