package com.agrotep.imp.exp.controller;

import com.agrotep.imp.exp.dto.TransportationDetailsDto;
import com.agrotep.imp.exp.dto.TransportationDto;
import com.agrotep.imp.exp.service.TransportationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 *
 * @author prog
 */
@Controller
@RequestMapping("/transportation-details")
@RequiredArgsConstructor
public class TransportationDetailsController {
    private final TransportationService service;

    @GetMapping("/{id}")
    public String getTranstortationDetails(@PathVariable Long id,  Model model) {
        Optional<TransportationDetailsDto> details = service.findTransportationDetailsById(id);
        if (details.isPresent()) {
            TransportationDetailsDto dto = details.get();
            model.addAttribute("transportation", dto);
            return "update-transportation-details";
        }
        model.addAttribute("transportation", new TransportationDetailsDto());
        return "add-transportation-details";
    }

    @PostMapping(value = "/update", consumes = {"application/x-www-form-urlencoded;charset=UTF-8"})
    public String updateTransportation(@RequestBody TransportationDetailsDto transportation, Model model) {
        TransportationDto transportationDto = service.save(transportation);
        model.addAttribute(transportationDto);
        return "transportation-details";
    }
}
