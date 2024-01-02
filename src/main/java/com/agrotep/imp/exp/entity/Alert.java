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
public class Alert {
    @EqualsAndHashCode.Include
    private Long id;
    private LocalDateTime time;
    private Person creator;
    private String text;
}
