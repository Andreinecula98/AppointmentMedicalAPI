package com.ApiTheBest.AppointmentAPI.model.appointmentModel.addAppointment;


import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * addAppointmentReq
 * <p>
 * Add a new Appointment to the DB.
 *
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "appointment"
})

public class AddAppointmentRequest {

    /**
     * the appointment inserted into DB
     *
     */
    @JsonProperty("appointment")
    @JsonPropertyDescription(" the appointment inserted into DB")
    @Valid
    private AddAppointment appointment;

    @JsonProperty("patientId")
    @JsonPropertyDescription(" the patientId ")
    @Valid
    private Integer patientId;

    @JsonProperty("doctorId")
    @JsonPropertyDescription(" the doctorId ")
    @Valid
    private Integer doctorId;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * the appointment inserted into DB
     *
     */
    @JsonProperty("appointment")
    public AddAppointment getAppointment() {
        return appointment;
    }

    /**
     * the appointment inserted into DB
     *
     */
    @JsonProperty("appointment")
    public void setAppointment(AddAppointment appointment) {
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



}
