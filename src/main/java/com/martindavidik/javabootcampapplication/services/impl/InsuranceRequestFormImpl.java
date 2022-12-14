package com.martindavidik.javabootcampapplication.services.impl;

import com.martindavidik.javabootcampapplication.domain.InsuranceRequestForm;
import com.martindavidik.javabootcampapplication.repositories.InsuranceRequestFormRepository;
import com.martindavidik.javabootcampapplication.services.InsuranceRequestFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InsuranceRequestFormImpl implements InsuranceRequestFormService {

    @Autowired
    private InsuranceRequestFormRepository insuranceRequestFormRepository;

    @Override
    public Optional<InsuranceRequestForm> findByPolicyNumber(String policyNumber) {
        return insuranceRequestFormRepository.findByPolicyNumber(policyNumber);
    }

    @Override
    public InsuranceRequestForm save(InsuranceRequestForm insuranceRequestForm) {
        return insuranceRequestFormRepository.save(insuranceRequestForm);
    }

    @Override
    public void delete(InsuranceRequestForm insuranceRequestForm) {
        insuranceRequestFormRepository.delete(insuranceRequestForm);
    }
}
