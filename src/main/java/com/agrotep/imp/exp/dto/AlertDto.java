package com.agrotep.imp.exp.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AlertDto {
    private Long id;
    private LocalDateTime time;
    private String dateStr;
    private String creatorName;
    private String text;
    private String backgroundClass;
}
