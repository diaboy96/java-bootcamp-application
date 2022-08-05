package com.martindavidik.javabootcampapplication.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class InsuranceRequestForm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Size(min = 5, max = 35)
    private String policyNumber;

    @Size(min = 2, max = 40)
    @Pattern(regexp = "^[A-Za-z]*$")
    private String name;

    @Size(min = 2, max = 40)
    @Pattern(regexp = "^[A-Za-z]*$")
    private String surname;

    @Size(max = 100)
    private String requestField;

    public InsuranceRequestForm() {
    }

    public InsuranceRequestForm(String policyNumber, String name, String surname, String requestField) {
        this.policyNumber = policyNumber;
        this.name = name;
        this.surname = surname;
        this.requestField = requestField;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getRequestField() {
        return requestField;
    }

    public void setRequestField(String requestField) {
        this.requestField = requestField;
    }
}
