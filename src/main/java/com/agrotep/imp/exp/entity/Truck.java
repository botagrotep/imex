package com.agrotep.imp.exp.entity;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Truck {
    @EqualsAndHashCode.Include
    private Long id;
    private String equipage;
    private String driver;
    private LocalDateTime operationalStatusCommentDateTime;
    private String operationalStatusComment;
    private String status;
    private String ekmt;
    private String dangerousStatus;

    private String truckType;
    private Boolean isInCarPark;
    private Boolean isAdrAcceptable;
    private Boolean isAdr1Acceptable;
    private String domesticCompany;
}
