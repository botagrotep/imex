package com.agrotep.imp.exp.controller;

import com.agrotep.imp.exp.dto.TransportationDetailsDto;
import com.agrotep.imp.exp.dto.TransportationDto;
import com.agrotep.imp.exp.service.TransportationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
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

    @GetMapping("update/{id}")
    public String getTranstortationDetails(@PathVariable Long id, Model model) {
        Optional<TransportationDetailsDto> details = service.findTransportationDetailsById(id);
        if (details.isPresent()) {
            TransportationDetailsDto dto = details.get();
            model.addAttribute("transportation", dto);
            addListsToModel(model);
            return "update-transportation-details";
        }
        return this.createTransportationDetails(model);
    }

    @PostMapping("/update")
    public String updateTransportation(@ModelAttribute TransportationDetailsDto transportation, Model model) {
        TransportationDto transportationDto = service.save(transportation);
        model.addAttribute(transportationDto);
        return "redirect:/import-export";
    }

    @GetMapping("/add")
    public String createTransportationDetails(Model model) {
        model.addAttribute("transportation", new TransportationDetailsDto());
        addListsToModel(model);
        return "add-transportation-details";
    }

    @PostMapping("/add")
    public String addTransportation(Authentication authentication,
                                    @ModelAttribute @Valid TransportationDetailsDto transportation, Model model) {
        transportation.setManagerName(authentication.getName());
        TransportationDto transportationDto = service.save(transportation);
        model.addAttribute(transportationDto);
        return "redirect:/import-export";
    }


    private void addListsToModel(Model model) {
        addCompaniesToModel(model);
        addCountriesToModel(model);
        addBorderCrossingPointsToModel(model);
    }

    private void addBorderCrossingPointsToModel(Model model) {
        Collection<String> borderCrossingPoints = service.getBorderCrossingPoints();
        model.addAttribute("borderCrossingPoints", borderCrossingPoints);
    }

    private void addCountriesToModel(Model model) {
        Collection<String> countries = service.getCountries();
        model.addAttribute("countries", countries);
    }

    private void addCompaniesToModel(Model model) {
        Collection<String> companies = service.getCompanies();
        model.addAttribute("clientCompanies", companies);
    }
}
