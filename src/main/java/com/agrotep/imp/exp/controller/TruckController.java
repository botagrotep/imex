package com.agrotep.imp.exp.controller;

import com.agrotep.imp.exp.dto.TransportationDto;
import com.agrotep.imp.exp.dto.TruckDto;
import com.agrotep.imp.exp.service.TransportationService;
import com.agrotep.imp.exp.service.TruckService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 *
 * @author prog
 */
@Controller
@RequestMapping("/truck")
@RequiredArgsConstructor
public class TruckController {
    private final TransportationService transportationService;
    private final TruckService service;

    @GetMapping("/{transportationId}")
    public String getTruckDetails(@PathVariable Long transportationId, Model model) {
        TransportationDto dto = transportationService.findTransportationById(transportationId)
                .orElse(new TransportationDto());
        model.addAttribute("transportation", dto);

        List<TruckDto> trucks = service.findAllWithCalculateRadiusFrom(transportationId);
        model.addAttribute("trucks", trucks);

        TruckDto truckDto = TruckDto.builder()
                .transportationComment(dto.getTransportationComment())
                .build();
        model.addAttribute("truck", truckDto);

        return "truck";
    }

    @PostMapping("/assign/{truckId}/to/transportation/{transportationId}")
    public String assignTruck(@PathVariable Long truckId, @PathVariable Long transportationId) {
              transportationService.setTruck(truckId, transportationId);
        return "redirect:/import-export";
    }

    @GetMapping("/assign/{truckId}/to/transportation/{transportationId}")
    public String assignTruckViaGet(@PathVariable Long truckId, @PathVariable Long transportationId) {
              transportationService.setTruck(truckId, transportationId);
        return "redirect:/import-export";
    }
}
