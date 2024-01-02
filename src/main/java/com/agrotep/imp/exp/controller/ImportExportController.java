package com.agrotep.imp.exp.controller;

import com.agrotep.imp.exp.dto.AlertDto;
import com.agrotep.imp.exp.dto.TransportationDetailsDto;
import com.agrotep.imp.exp.service.AlertService;
import com.agrotep.imp.exp.service.PersonService;
import com.agrotep.imp.exp.service.TransportationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping({"/", "/import-export"})
@RequiredArgsConstructor
public class ImportExportController {
    private static final String DATE_FROM = "date_from";
    private static final String DATE_TO = "date_to";
    private static final String BORDER_CROSSING_POINT = "border_crossing_point";
    private static final String FILTERS = "filters";
    private final TransportationService service;
    private final PersonService personService;
    private final AlertService alertservice;

    @GetMapping
    public String getTransportations(@RequestParam(name = FILTERS, required = false) List<String> filters,
                                     @RequestParam(name= DATE_FROM, required = false) LocalDate dateFrom,
                                     @RequestParam(name= DATE_TO, required = false) LocalDate dateTo,
                                     @RequestParam(name = BORDER_CROSSING_POINT, required = false) String borderCrossingPoint,
                                     Authentication authentication, Model model) {
        if (dateFrom == null) {
            dateFrom = LocalDate.now();
        }
        if (dateTo == null) {
            dateTo = LocalDate.now().plusWeeks(1);
        }
        model.addAttribute("transportations", service.findAllFiltered(filters, dateFrom, dateTo,
                borderCrossingPoint));
        model.addAttribute("transportationForComment", new TransportationDetailsDto());
        model.addAttribute("currentUser", personService.findByName(authentication.getName()));

        model.addAttribute(FILTERS, filters);
        model.addAttribute(BORDER_CROSSING_POINT, borderCrossingPoint);
        model.addAttribute(DATE_FROM, dateFrom);
        model.addAttribute(DATE_TO, dateTo);

        Collection<String> borderCrossingPoints = service.getBorderCrossingPoints();
        model.addAttribute("borderCrossingPoints", borderCrossingPoints);

        Map<String, List<AlertDto>> alerts = alertservice.findAll();
        model.addAttribute("alerts", alerts);
        model.addAttribute("newAlert", new AlertDto());
        model.addAttribute("editedAlert", new AlertDto());
        return "import-export";
    }

    @PostMapping("comment/{transportationId}/update")
    public String updateComment(@PathVariable Long transportationId,
                                @ModelAttribute TransportationDetailsDto transportationForComment) {
        String comment = transportationForComment.getComment();
        service.findTransportationDetailsById(transportationId)
                .ifPresent(t -> {
                    t.setComment(comment);
                    service.saveOrCopy(t);
                });
        return "redirect:/import-export";
    }

    @PostMapping("add/additional/loading")
    public String createAdditionalLoadingRequest(Authentication authentication,
                                                @ModelAttribute("newAlert") AlertDto newAlert) {
        newAlert.setCreatorName(authentication.getName());
        newAlert.setTime(LocalDateTime.now());
        alertservice.save(newAlert);
        return "redirect:/import-export";
    }

    @PostMapping(value = "edit/additional/loading", params = "submit")
    public String editAdditionalLoadingRequest(Authentication authentication,
                                                @ModelAttribute("editedAlert") AlertDto editedAlert) {
        editedAlert.setCreatorName(authentication.getName());
        editedAlert.setTime(LocalDateTime.now());
        alertservice.save(editedAlert);
        return "redirect:/import-export";
    }

    @PostMapping(value = "edit/additional/loading", params = "cancel")
    public String removeAdditionalLoadingRequest(@ModelAttribute("editedAlert") AlertDto editedAlert) {
        alertservice.delete(editedAlert);
        return "redirect:/import-export";
    }
}
