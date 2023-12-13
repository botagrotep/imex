package com.agrotep.imp.exp.controller;

import com.agrotep.imp.exp.dto.LoadingDto;
import com.agrotep.imp.exp.dto.TransportationDetailsDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/transportation-loading")
public class TransportationLoadingController {
    public String updateLoading(@ModelAttribute LoadingDto loading, Model model) {

        return "transportation-details/13";
    }
}
