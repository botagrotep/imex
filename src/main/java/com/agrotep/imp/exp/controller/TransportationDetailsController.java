package com.agrotep.imp.exp.controller;

import com.agrotep.imp.exp.dto.*;
import com.agrotep.imp.exp.entity.Loading;
import com.agrotep.imp.exp.entity.enums.LoadingType;
import com.agrotep.imp.exp.service.TransportationService;
import com.agrotep.imp.exp.service.TruckService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static com.agrotep.imp.exp.entity.enums.LoadingType.*;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

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
            addEmptyLoadingIfAbsent(dto);
            model.addAttribute("transportation", dto);
            model.addAttribute("loadingTypes", LOADING_TYPES);
            model.addAttribute("loadingTypesMap", Stream.of(LoadingType.values())
                    .collect(Collectors.toMap(t -> t.name(), t -> t.getNotation(), (t1, t2) -> t2, LinkedHashMap::new )));
            model.addAttribute("unloadingTypes", UNLOADING_TYPES);
            model.addAttribute("editedLoading", Loading.builder().build());
            addListsToModel(model);
            return "update-transportation-details";
        }
        return this.createTransportationDetails(model);
    }

    @PostMapping(value = "/update", params = "submit")
    public String updateTransportation(@ModelAttribute TransportationDetailsDto transportation, Model model) {
        removeStubLoading(transportation);
        TransportationDto transportationDto = service.saveOrCopy(transportation);
        model.addAttribute(transportationDto);
        return "redirect:/import-export";
    }


    @PostMapping(value = "/update", params = "cancel")
    public String cancelTransportation(@ModelAttribute TransportationDetailsDto transportation, Model model) {
        removeStubLoading(transportation);
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
        TransportationDetailsDto dto = new TransportationDetailsDto();
        dto.setLoadings(new ArrayList<>(List.of(
                LoadingDto.builder().loadingNo(1).loadingType(SIMPLE_LOADING).build(),
                LoadingDto.builder().loadingNo(2).loadingType(SIMPLE_UNLOADING).build(),
                new LoadingDto()
        )));
        model.addAttribute("transportation", dto);
        addListsToModel(model);
        model.addAttribute("loadingTypes", LOADING_TYPES);
        model.addAttribute("unloadingTypes", UNLOADING_TYPES);
        return "add-transportation-details";
    }

    @PostMapping("/add")
    public String addTransportation(Authentication authentication,
                                    @ModelAttribute @Valid TransportationDetailsDto transportation, Model model) {
        removeStubLoading(transportation);
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

    private static void removeStubLoading(TransportationDetailsDto transportation) {
        transportation.getLoadings().removeIf(l -> !StringUtils.hasText(l.getLoadingDate()));
    }

    private void addEmptyLoadingIfAbsent(TransportationDetailsDto dto) {
        final List<LoadingDto> loadings = dto.getLoadings();
        if (!CollectionUtils.isEmpty(loadings)) {
            return;
        } 
        if (loadings == null) {
            dto.setLoadings(new ArrayList<>());
        }
        dto.getLoadings().add(new LoadingDto());
    }
}
