package com.agrotep.imp.exp.controller;

import com.agrotep.imp.exp.dto.TransportationDetailsDto;
import com.agrotep.imp.exp.service.PersonService;
import com.agrotep.imp.exp.service.TransportationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

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
        return "import-export";
    }

    @PostMapping("comment/{transportationId}/update")
    public String updateComment(@PathVariable Long transportationId, @ModelAttribute TransportationDetailsDto transportationForComment) {
        String comment = transportationForComment.getComment();
        service.findTransportationDetailsById(transportationId)
                .ifPresent(t -> {
                    t.setComment(comment);
                    service.saveOrCopy(t);
                });
        return "redirect:/import-export";
    }
}
