package com.agrotep.imp.exp.controller;

import com.agrotep.imp.exp.dto.TransportationDetailsDto;
import com.agrotep.imp.exp.service.PersonService;
import com.agrotep.imp.exp.service.TransportationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping({"/", "/import-export"})
@RequiredArgsConstructor
public class ImportExportController {
    private final TransportationService service;
    private final PersonService personService;

    @GetMapping
    public String getTransportations(Authentication authentication, Model model) {
        model.addAttribute("transportations", service.findAll());
        model.addAttribute("transportationForComment", new TransportationDetailsDto());
        model.addAttribute("currentUser", personService.findByName(authentication.getName()));
        return "import-export";
    }

    @PostMapping("comment/{transportationId}/update")
    public String updateComment(@PathVariable Long transportationId, @ModelAttribute TransportationDetailsDto transportationForComment) {
        String comment = transportationForComment.getComment();
        service.findTransportationDetailsById(transportationId)
                .ifPresent(t -> {
                    t.setComment(comment);
                    service.save(t);
                });
        return "redirect:/import-export";
    }
}
