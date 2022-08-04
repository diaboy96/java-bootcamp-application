package com.martindavidik.javabootcampapplication.service;

import com.martindavidik.javabootcampapplication.domain.InsuranceRequestForm;

import java.util.Optional;

public interface InsuranceRequestFormService {

    Optional<InsuranceRequestForm> findByPolicyNumber(String policyNumber);

    InsuranceRequestForm save(InsuranceRequestForm insuranceRequestForm);

    void delete(InsuranceRequestForm insuranceRequestForm);
}
