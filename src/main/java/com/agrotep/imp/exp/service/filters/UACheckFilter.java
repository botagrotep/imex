package com.agrotep.imp.exp.service.filters;

import com.agrotep.imp.exp.dto.enums.TransportationFilterType;
import com.agrotep.imp.exp.entity.Loading;
import com.agrotep.imp.exp.entity.Transportation;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.function.Predicate;

@Getter
@Component
public class UACheckFilter implements ImportExportFilter {
    private final TransportationFilterType type = TransportationFilterType.UA_CHECK;

    @Override
    public Predicate<Transportation> getPredicate() {
        return (t) -> t.getLoadings().stream()
                .map(Loading::getLoadingCountry)
                .allMatch(UA::equalsIgnoreCase);
    }
}
