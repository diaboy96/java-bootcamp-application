package com.martindavidik.javabootcampapplication.repositories;

import com.martindavidik.javabootcampapplication.domain.InsuranceRequestForm;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface InsuranceRequestFormRepository extends CrudRepository<InsuranceRequestForm, Integer> {

    Optional<InsuranceRequestForm> findByPolicyNumber(String policyNumber);
}
