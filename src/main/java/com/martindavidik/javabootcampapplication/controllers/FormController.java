package com.martindavidik.javabootcampapplication.controllers;

import com.martindavidik.javabootcampapplication.domain.InsuranceRequestForm;
import com.martindavidik.javabootcampapplication.services.InsuranceRequestFormService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Valid;

@Controller
public class FormController implements WebMvcConfigurer {

    private final InsuranceRequestFormService insuranceRequestFormService;

    /**
     * Constructor
     *
     * @param insuranceRequestFormService InsuranceRequestFormService
     */
    public FormController(InsuranceRequestFormService insuranceRequestFormService) {
        this.insuranceRequestFormService = insuranceRequestFormService;
    }

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

        saveFormDataToDB(insuranceRequestForm);

        return "redirect:/form-successfully-sent";
    }

    /**
     * Saves insuranceRequestForm to database
     *
     * @param insuranceRequestForm InsuranceRequestForm
     */
    private void saveFormDataToDB(@Valid InsuranceRequestForm insuranceRequestForm) {
        insuranceRequestFormService.save(insuranceRequestForm);
    }
}
