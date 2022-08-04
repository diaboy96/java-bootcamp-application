package com.martindavidik.javabootcampapplication.controller;

import com.martindavidik.javabootcampapplication.domain.InsuranceRequestForm;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Valid;

@Controller
public class FormController implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/form-successfully-sent").setViewName("form-successfully-sent");
    }

    @GetMapping("/")
    public String showInsuranceRequestForm(InsuranceRequestForm insuranceRequestForm) {
        return "insurance-request-form";
    }

    @PostMapping("/")
    public String validateInsuranceRequestForm(@Valid InsuranceRequestForm insuranceRequestForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "insurance-request-form";
        }

        // Todo: insert data to database

        return "redirect:/form-successfully-sent";
    }
}
