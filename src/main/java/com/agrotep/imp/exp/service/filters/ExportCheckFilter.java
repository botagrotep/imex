package com.agrotep.imp.exp.service.filters;

import com.agrotep.imp.exp.dto.enums.TransportationFilterType;
import com.agrotep.imp.exp.entity.Loading;
import com.agrotep.imp.exp.entity.Transportation;
import lombok.Getter;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.function.Predicate;

@Getter
@Component
public class ExportCheckFilter implements ImportExportFilter {
    private final TransportationFilterType type = TransportationFilterType.EXPORT_CHECK;

    @Override
    public Predicate<Transportation> getPredicate() {
        return (t) -> {
            List<Loading> loadings = t.getLoadings();
            if (CollectionUtils.isEmpty(loadings)) {
                return false;
            }
            Loading loading = CollectionUtils.firstElement(loadings);
            if (loading == null) {
                return false;
            }
            Loading unloading = CollectionUtils.lastElement(loadings);
            if (unloading == null) {
                return false;
            }
            String loadingCountry = loading.getLoadingCountry();
            String unloadingCountry = unloading.getLoadingCountry();
            return UA.equalsIgnoreCase(loadingCountry) && !UA.equalsIgnoreCase(unloadingCountry);
        };
    }
}
