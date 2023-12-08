package com.agrotep.imp.exp.controller;

import com.agrotep.imp.exp.dto.TruckDto;
import com.agrotep.imp.exp.entity.Transportation;
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
@Controller
@RequiredArgsConstructor
public class TruckController {
    private final TransportationService service;

    @PostMapping("/truck")
    @PutMapping("/truck")
    public Transportation createTruck(@RequestBody TruckDto t) {
        return service.saveTruck(t);
    }
}
