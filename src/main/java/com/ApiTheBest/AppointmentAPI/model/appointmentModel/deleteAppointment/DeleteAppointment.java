package com.ApiTheBest.AppointmentAPI.model.appointmentModel.deleteAppointment;


import com.ApiTheBest.AppointmentAPI.model.doctorModel.Doctor;

import com.ApiTheBest.AppointmentAPI.model.patientModel.Patient;
import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "appointmentId",
        "patient",
        "doctor",
        "appointmentDate",
        "appointmentTime"
})

public class DeleteAppointment {
    @JsonProperty("patient")
    @JsonPropertyDescription(" The patient ")
    @NotNull

    private Patient patient;

    @JsonProperty("doctor")
    @JsonPropertyDescription(" The doctor")
    private Doctor doctor;

    @JsonProperty("appointmentDate")
    @JsonPropertyDescription(" The appointmentDate.")
    private LocalDate appointmentDate;

    @JsonProperty("appointmentTime")
    @JsonPropertyDescription(" The appointmentDate.")
    private LocalTime appointmentTime;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();


    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
