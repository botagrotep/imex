package com.agrotep.imp.exp.service.filters;

import com.agrotep.imp.exp.dto.enums.TransportationFilterType;
import com.agrotep.imp.exp.entity.Transportation;
import lombok.Getter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.function.Predicate;

@Getter
@Component
public class WoEquipageCheckFilter implements ImportExportFilter {
    private final TransportationFilterType type = TransportationFilterType.WO_EQUIPAGE_CHECK;

    @Override
    public Predicate<Transportation> getPredicate() {
        return (t) -> t.getTruck() == null || !StringUtils.hasText(t.getTruck().getEquipage());
    }
}
