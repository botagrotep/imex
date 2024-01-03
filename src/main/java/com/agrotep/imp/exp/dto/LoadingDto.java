package com.agrotep.imp.exp.dto;

import com.agrotep.imp.exp.entity.enums.LoadingType;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
