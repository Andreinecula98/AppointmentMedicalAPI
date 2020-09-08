package com.ApiTheBest.AppointmentAPI.model.appointmentModel.updateAppointment;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.validation.constraints.*;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "appointmentId",
        "patientName",
        "doctorName",
        "appointmentDate",
        "appointmentDate"
})


public class UpdateAppointment {

    @JsonProperty("appointmentId")
    @JsonPropertyDescription(" Appointment ID ")
    @NotNull

    private Integer appointmentId;

    @JsonProperty("patientName")
    @JsonPropertyDescription(" The patient name ")
    @NotNull

    private String patientName;

    @JsonProperty("doctorName")
    @JsonPropertyDescription(" The doctor name")
    private String doctorName;

    @JsonProperty("appointmentDate")
    @JsonPropertyDescription(" The appointmentDate.")
    private LocalDate appointmentDate;

    @JsonProperty("appointmentTime")
    @JsonPropertyDescription(" The appointmentDate.")
    private LocalTime appointmentTime;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @Override
    public String toString() {
        return "UpdateAppointment{" +
                "patientName=" + patientName +
                ", doctorName=" + doctorName +
                ", appointmentDate=" + appointmentDate +
                ", appointmentTime=" + appointmentTime +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}
