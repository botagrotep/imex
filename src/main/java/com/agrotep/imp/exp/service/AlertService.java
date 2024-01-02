package com.agrotep.imp.exp.service;

import com.agrotep.imp.exp.dto.AlertDto;
import com.agrotep.imp.exp.entity.Alert;
import com.agrotep.imp.exp.repository.AlertRepository;
import com.agrotep.imp.exp.service.converter.AlertDtoConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AlertService {
    private final AlertRepository repository;
    private final AlertDtoConverter converter;
    public Map<String, List<AlertDto>> findAll() {
        return converter.toAlertDto(repository.findAllOrderByTime()).stream()
                .collect(Collectors.groupingBy(AlertDto::getDateStr));
    }


    public void save(AlertDto dto) {
        Alert alert = converter.toAlert(dto);
        repository.save(alert);
    }

    public void delete(AlertDto alert) {
        repository.delete(converter.toAlert(alert));
    }
}
