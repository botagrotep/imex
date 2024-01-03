package com.agrotep.imp.exp.entity;

import com.agrotep.imp.exp.entity.enums.LoadingType;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Builder(toBuilder = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Loading {
    @EqualsAndHashCode.Include
    private Long id;
    private String loadingCity;
    private String loadingCountry;

    private Integer loadingNo;
    private LocalDate loadingDate;
    private LocalTime loadingTime;
    private String loadingCityPostalIndex;
    private String loadingRegion;
    private String loadingAddress;
    private Double loadingLongitude;
    private Double loadingLatitude;
    private LoadingType loadingType;
}
