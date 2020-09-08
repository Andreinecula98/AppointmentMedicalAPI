package com.ApiTheBest.AppointmentAPI.model.patientModel.addPatient;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.validation.Valid;

/**
 * addCustomerReq
 * <p>
 * Add a new Customer to the DB.
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "patient"
})
public class AddPatientRequest {

    /**
     * the patient inserted into DB
     *
     */
    @JsonProperty("patient")
    @JsonPropertyDescription(" the patient inserted into DB")
    @Valid
    private AddPatient patient;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * the patient inserted into DB
     *
     */
    @JsonProperty("patient")
    public AddPatient getPatient() {
        return patient;
    }

    /**
     * the customer inserted into DB
     *
     */
    @JsonProperty("patient")
    public void setPatient(AddPatient patient) {
        this.patient = patient;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return "AddPatientRequest{" +
                "patient=" + patient +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}