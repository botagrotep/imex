package com.agrotep.imp.exp.dto;

import com.agrotep.imp.exp.entity.Person;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TransportationDto {

    private Long id;
    private LocalDate transportationDate;
    private String direction;
    private String typeOfTruck;
    private String clientCompany;
    private String managerName;
    private Long managerId;
    private String cargo;
    private String conditions;
    private String coordinatorComment;
    private String transportationComment;
    private String equipage = "";
    private String driver = "";
    private String comment;
}
