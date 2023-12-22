package com.agrotep.imp.exp.service.filters;

import com.agrotep.imp.exp.dto.TransportationFilterType;
import com.agrotep.imp.exp.entity.Transportation;

import java.util.function.Predicate;

public interface ImportExportFilter {
    String UA = "UA";
    TransportationFilterType getType();
    Predicate<Transportation> getPredicate();
}
