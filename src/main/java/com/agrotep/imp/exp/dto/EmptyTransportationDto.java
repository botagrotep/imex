package com.agrotep.imp.exp.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EmptyTransportationDto {
    private Long id;
    @NotNull
    private LocalDate loadingDate;
    private String loadingCountry;
    private String unloadingCountry;
    private String borderCrossingPoint;
    private String truckId;
    private String coordinatorComment;
    private String managerName;
}
