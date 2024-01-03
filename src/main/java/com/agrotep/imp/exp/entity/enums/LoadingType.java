package com.agrotep.imp.exp.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Getter
public enum LoadingType {
    SIMPLE_LOADING("Зав"), COMPLEX_LOADING("ЗавЗам"),
    SIMPLE_UNLOADING("Розв"), COMPLEX_UNLOADING("РозвРозм");
    public static final List<LoadingType> LOADING_TYPES = Arrays.asList(SIMPLE_LOADING, COMPLEX_LOADING);
    public static final List<LoadingType> UNLOADING_TYPES = Arrays.asList(SIMPLE_UNLOADING, COMPLEX_UNLOADING);
    private final String notation;
}
