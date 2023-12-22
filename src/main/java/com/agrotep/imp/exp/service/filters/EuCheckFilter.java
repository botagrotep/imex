package com.agrotep.imp.exp.service.filters;

import com.agrotep.imp.exp.dto.TransportationFilterType;
import com.agrotep.imp.exp.entity.Transportation;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.function.Predicate;

@Getter
@Component
public class EuCheckFilter implements ImportExportFilter {
    private final TransportationFilterType type = TransportationFilterType.EU_CHECK;

    @Override
    public Predicate<Transportation> getPredicate() {
        return (t) -> {
            String loadingCountry = t.getLoadingCountry();
            String unloadingCountry = t.getUnloadingCountry();
            return !UA.equalsIgnoreCase(loadingCountry) && !UA.equalsIgnoreCase(unloadingCountry);
        };
    }
}