package com.agrotep.imp.exp.controller;

import com.agrotep.imp.exp.service.TransportationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("/import-export")
@RequiredArgsConstructor
public class ImportExportController {
    private final TransportationService service;

    @GetMapping
    public String getTransportations(Model model) {
        model.addAttribute("transportations", service.findAll());
        return "import-export";
    }
}
