package com.martindavidik.javabootcampapplication.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class InsuranceRequestForm {

    @Size(min = 5, max = 35)
    private String policyNumber;

    @Size(min = 2, max = 60)
    @Pattern(regexp = "^[A-Za-z]*$")
    private String name;

    @Size(min = 2, max = 60)
    @Pattern(regexp = "^[A-Za-z]*$")
    private String surname;

    @NotNull
    @Size(max = 300)
    private String requestField;

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

    public String toString() {
        return "InsuranceRequest(policyNumber: " + this.policyNumber + ", name: " + this.name + ", surname: " + this.surname + ", requestField: " + this.requestField + ")";
    }
}
