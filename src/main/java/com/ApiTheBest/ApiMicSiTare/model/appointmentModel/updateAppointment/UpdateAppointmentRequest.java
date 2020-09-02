package com.ApiTheBest.ApiMicSiTare.model.appointmentModel.updateAppointment;

import java.util.HashMap;
import java.util.Map;

import com.ApiTheBest.ApiMicSiTare.model.patientModel.updatePatient.UpdatePatient;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.validation.Valid;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "appointment"
})
public class UpdateAppointmentRequest {


    @JsonProperty("appointment")
    @JsonPropertyDescription(" the appointment updated into DB")
    @Valid
    private UpdatePatient appointment;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();


    @JsonProperty("pacient")
    public UpdatePatient getAppointment() {
        return appointment;
    }


    @JsonProperty("appointment")
    public void setAppointment(UpdatePatient appointment) {
        this.appointment = appointment;
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
        return "UpdateAppointmentRequest{" +
                "appointment=" + appointment +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}
