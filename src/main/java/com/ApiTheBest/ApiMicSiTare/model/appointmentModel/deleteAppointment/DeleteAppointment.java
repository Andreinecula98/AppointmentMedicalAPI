package com.ApiTheBest.ApiMicSiTare.model.appointmentModel.deleteAppointment;


import com.ApiTheBest.ApiMicSiTare.model.doctorModel.Doctor;
import com.ApiTheBest.ApiMicSiTare.model.patientModel.Patient;
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
        "pacient",
        "doctor",
        "appointmentDate",
        "appointmentTime"
})

public class DeleteAppointment {
    @JsonProperty("pacient")
    @JsonPropertyDescription(" The pacient ")
    @NotNull

    private Patient pacient;

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
