package com.agrotep.imp.exp.config;

import com.agrotep.imp.exp.dto.TransportationFilterType;
import com.agrotep.imp.exp.service.filters.ImportExportFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
public class FiltersConfig {
    private final List<ImportExportFilter> filterComponents;

    @Bean
    Map<TransportationFilterType, ImportExportFilter> importExportFilterMap() {
        Map<TransportationFilterType, ImportExportFilter> map = filterComponents.stream()
                .collect(Collectors.toMap(ImportExportFilter::getType, f -> f, (a, b) -> b));
        Map<TransportationFilterType, ImportExportFilter> enumMap = new EnumMap<>(TransportationFilterType.class);
        enumMap.putAll(map);
        return enumMap;
    }
}
