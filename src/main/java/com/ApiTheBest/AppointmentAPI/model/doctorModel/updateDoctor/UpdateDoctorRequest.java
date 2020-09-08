package com.ApiTheBest.AppointmentAPI.model.doctorModel.updateDoctor;

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


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "doctor"
})

public class UpdateDoctorRequest {

    @JsonProperty("doctor")
    @JsonPropertyDescription(" the doctor updated into DB")
    @Valid
    private UpdateDoctor doctor;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * the doctor updated into DB
     *
     */
    @JsonProperty("doctor")
    public UpdateDoctor getDoctor() {
        return doctor;
    }

    /**
     * the doctor updated into DB
     *
     */
    @JsonProperty("doctor")
    public void setDoctor(UpdateDoctor doctor) {
        this.doctor = doctor;
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
