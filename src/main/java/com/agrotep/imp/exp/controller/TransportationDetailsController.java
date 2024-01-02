package com.agrotep.imp.exp.controller;

import com.agrotep.imp.exp.dto.EmptyTransportationDto;
import com.agrotep.imp.exp.dto.TransportationDetailsDto;
import com.agrotep.imp.exp.dto.TransportationDto;
import com.agrotep.imp.exp.dto.TruckDto;
import com.agrotep.imp.exp.service.TransportationService;
import com.agrotep.imp.exp.service.TruckService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * @author prog
 */
@Controller
@RequestMapping("/transportation-details")
@RequiredArgsConstructor
public class TransportationDetailsController {
    private final TransportationService service;
    private final TruckService truckService;

    @GetMapping("/update/{id}")
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

    @PostMapping(value = "/update", params = "submit")
    public String updateTransportation(@ModelAttribute TransportationDetailsDto transportation, Model model) {
        TransportationDto transportationDto = service.saveOrCopy(transportation);
        model.addAttribute(transportationDto);
        return "redirect:/import-export";
    }


    @PostMapping(value = "/update", params = "cancel")
    public String cancelTransportation(@ModelAttribute TransportationDetailsDto transportation, Model model) {
        TransportationDto transportationDto = service.cancel(transportation);
        model.addAttribute(transportationDto);
        return "redirect:/import-export";
    }

    @GetMapping("/empty")
    public String getEmptyTransportation(Model model) {
        model.addAttribute("transportation", new EmptyTransportationDto());
        addListsToModel(model);
        return "empty-transportation-details";
    }

    @PostMapping("empty/add")
    public String addEmptyTransportation(Authentication authentication,
                                         @ModelAttribute @Valid EmptyTransportationDto transportation) {
        transportation.setManagerName(authentication.getName());
        service.save(transportation);
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
        TransportationDto transportationDto = service.saveOrCopy(transportation);
        model.addAttribute(transportationDto);
        return "redirect:/import-export";
    }


    private void addListsToModel(Model model) {
        addCompaniesToModel(model);
        addCountriesToModel(model);
        addBorderCrossingPointsToModel(model);
        addTrucksToModel(model);
    }

    private void addBorderCrossingPointsToModel(Model model) {
        Collection<String> borderCrossingPoints = service.getBorderCrossingPoints();
        model.addAttribute("borderCrossingPoints", borderCrossingPoints);
    }

    private void addTrucksToModel(Model model) {
        List<TruckDto> dtos = truckService.findAll();
        model.addAttribute("trucks", dtos);
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
