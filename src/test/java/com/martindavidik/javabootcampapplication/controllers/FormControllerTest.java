package com.martindavidik.javabootcampapplication.controllers;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextArea;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import com.martindavidik.javabootcampapplication.domain.InsuranceRequestForm;
import com.martindavidik.javabootcampapplication.services.InsuranceRequestFormService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.htmlunit.MockMvcWebClientBuilder;
import org.springframework.web.context.WebApplicationContext;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

@SpringBootTest
public class FormControllerTest {

    private static final String INSURANCE_REQUEST_FORM = "insurance-request-form";
    private static final String INSURANCE_REQUEST_FORM_PAGE = "http://localhost";

    // form input values - for testing form validation
    private static final String POLICY_NUMBER_TOO_SHORT_VALUE = "a";
    private static final String NAME_BAD_CHARACTER_VALUE = "only letters are allowed 69";
    private static final String SURNAME_TOO_LONG_VALUE = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
    private static final String REQUEST_FIELD_TOO_LONG_VALUE = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";

    // expected error messages (output from validator)
    private static final String POLICY_NUMBER_ERROR_MESSAGE = "size must be between 5 and 35";
    private static final String NAME_ERROR_MESSAGE = "must match \"^[A-Za-z]*$\"";
    private static final String SURNAME_ERROR_MESSAGE = "size must be between 2 and 40";
    private static final String REQUEST_FIELD_ERROR_MESSAGE = "size must be between 0 and 100";

    // form input values saved in database
    private static final String POLICY_NUMBER_TESTING_VALUE = "test of policy number";
    private static final String NAME_TESTING_VALUE = "firstname";
    private static final String SURNAME_TESTING_VALUE = "lastname";
    private static final String REQUEST_FIELD_TESTING_VALUE = "value from request field";

    @Autowired
    private InsuranceRequestFormService insuranceRequestFormService;
    private WebClient webClient;

    @BeforeEach
    public void setup(WebApplicationContext context) {
        this.webClient = MockMvcWebClientBuilder.webAppContextSetup(context).build();
    }

    @Test
    public void testFormValidation() throws Exception {
        HtmlPage insuranceRequestFormPage = webClient.getPage(INSURANCE_REQUEST_FORM_PAGE);

        // policy number - input
        HtmlTextInput policyNumberInput = insuranceRequestFormPage.getHtmlElementById("policy-number");
        policyNumberInput.setValueAttribute(POLICY_NUMBER_TOO_SHORT_VALUE);
        // name - input
        HtmlTextInput nameInput = insuranceRequestFormPage.getHtmlElementById("name");
        nameInput.setValueAttribute(NAME_BAD_CHARACTER_VALUE);
        // surname - input
        HtmlTextInput surnameInput = insuranceRequestFormPage.getHtmlElementById("surname");
        surnameInput.setValueAttribute(SURNAME_TOO_LONG_VALUE);
        // request field - input
        HtmlTextArea requestFieldInput = insuranceRequestFormPage.getHtmlElementById("request-field");
        requestFieldInput.setText(REQUEST_FIELD_TOO_LONG_VALUE);
        // submit - input
        HtmlForm form = insuranceRequestFormPage.getHtmlElementById(INSURANCE_REQUEST_FORM);
        HtmlSubmitInput submit = form.getOneHtmlElementByAttribute("input", "type", "submit");
        HtmlPage formSubmittedPage = submit.click();

        // user successfully NOT redirected after submit (because of validation)
        assertThat(formSubmittedPage.getUrl().toString()).endsWith("/");

        // policy number - error message
        String policyNumberErrorMessage = formSubmittedPage.getHtmlElementById("policy-number-error").getTextContent();
        assertThat(policyNumberErrorMessage).isEqualTo(POLICY_NUMBER_ERROR_MESSAGE);
        // policy number - error message
        String nameErrorMessage = formSubmittedPage.getHtmlElementById("name-error").getTextContent();
        assertThat(nameErrorMessage).isEqualTo(NAME_ERROR_MESSAGE);
        // policy number - error message
        String surnameErrorMessage = formSubmittedPage.getHtmlElementById("surname-error").getTextContent();
        assertThat(surnameErrorMessage).isEqualTo(SURNAME_ERROR_MESSAGE);
        // request field - error message
        String requestFieldErrorMessage = formSubmittedPage.getHtmlElementById("request-field-error").getTextContent();
        assertThat(requestFieldErrorMessage).isEqualTo(REQUEST_FIELD_ERROR_MESSAGE);
    }

    @Test
    public void testFormSubmit() throws Exception {
        HtmlPage insuranceRequestFormPage = webClient.getPage(INSURANCE_REQUEST_FORM_PAGE);

        // policy number - input
        HtmlTextInput policyNumberInput = insuranceRequestFormPage.getHtmlElementById("policy-number");
        policyNumberInput.setValueAttribute(POLICY_NUMBER_TESTING_VALUE);
        // name - input
        HtmlTextInput nameInput = insuranceRequestFormPage.getHtmlElementById("name");
        nameInput.setValueAttribute(NAME_TESTING_VALUE);
        // surname - input
        HtmlTextInput surnameInput = insuranceRequestFormPage.getHtmlElementById("surname");
        surnameInput.setValueAttribute(SURNAME_TESTING_VALUE);
        // request field - input
        HtmlTextArea requestFieldInput = insuranceRequestFormPage.getHtmlElementById("request-field");
        requestFieldInput.setText(REQUEST_FIELD_TESTING_VALUE);
        // submit - input
        HtmlForm form = insuranceRequestFormPage.getHtmlElementById(INSURANCE_REQUEST_FORM);
        HtmlSubmitInput submit = form.getOneHtmlElementByAttribute("input", "type", "submit");
        HtmlPage formSubmittedPage = submit.click();

        // successfully redirected after submit
        assertThat(formSubmittedPage.getUrl().toString()).endsWith("/form-successfully-sent");
    }

    @Test
    public void testFormDataSuccessfullySavedToDatabase() {
        Optional<InsuranceRequestForm> insuranceRequestFormOptional = insuranceRequestFormService.findByPolicyNumber(POLICY_NUMBER_TESTING_VALUE);

        if (insuranceRequestFormOptional.isPresent()) {
            InsuranceRequestForm insuranceRequestForm = insuranceRequestFormOptional.get();

            assertThat(insuranceRequestForm.getPolicyNumber()).isEqualTo(POLICY_NUMBER_TESTING_VALUE);
            assertThat(insuranceRequestForm.getName()).isEqualTo(NAME_TESTING_VALUE);
            assertThat(insuranceRequestForm.getSurname()).isEqualTo(SURNAME_TESTING_VALUE);
            assertThat(insuranceRequestForm.getRequestField()).isEqualTo(REQUEST_FIELD_TESTING_VALUE);
        } else {
            fail("Data was not in database");
        }
    }
}
