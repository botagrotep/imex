package com.agrotep.imp.exp.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Getter
public enum TransportationFilterType {

    MY_CHECK(true),
    EXPORT_CHECK(false),
    IMPORT_CHECK(false),
    UA_CHECK(false),
    EU_CHECK(false),
    WO_EQUIPAGE_CHECK(false),
    CUSTOM_FILTER(false);

    private static final Set<String> NAMES = Stream.of(TransportationFilterType.values())
            .map(Enum::name)
            .collect(Collectors.toSet());

    private final boolean isAnd;

    public static Set<TransportationFilterType> toFilters(Collection<String> filtersNames) {
        return filtersNames.stream()
                .filter(Objects::nonNull)
                .map(String::toUpperCase)
                .filter(NAMES::contains)
                .map(TransportationFilterType::valueOf)
                .collect(Collectors.toSet());
    }
}
