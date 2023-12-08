package com.agrotep.imp.exp.controller;

import com.agrotep.imp.exp.dto.TransportationDetailsDto;
import com.agrotep.imp.exp.dto.TransportationDto;
import com.agrotep.imp.exp.service.TransportationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author prog
 */
@Controller("/transportation-details")
@RequiredArgsConstructor
public class TransportationDetailsController {
    private final TransportationService service;

    @PostMapping
    @PutMapping
    public TransportationDto createTransportation(@RequestBody TransportationDetailsDto transportation) {
        return service.save(transportation);
    }
}
