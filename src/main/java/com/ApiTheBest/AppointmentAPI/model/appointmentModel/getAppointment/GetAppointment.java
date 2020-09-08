package com.ApiTheBest.AppointmentAPI.model.appointmentModel.getAppointment;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

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
        "appointmentTime"
})

public class GetAppointment {

    @JsonProperty("appointmentId")
    @JsonPropertyDescription(" appointmentId ")
    private Integer appointmentId;

    @JsonProperty("patientName")
    @JsonPropertyDescription(" The patient ")
    @NotNull

    private String patientName;

    @JsonProperty("doctorName")
    @JsonPropertyDescription(" The doctor")
    private String doctorName;

    @JsonProperty("appointmentDate")
    @JsonPropertyDescription(" The appointmentDate.")
    private LocalDate appointmentDate;

    @JsonProperty("appointmentTime")
    @JsonPropertyDescription(" The appointmentDate.")
    private LocalTime appointmentTime;

    @Override
    public String toString() {
        return "GetAppointment{" +
                "appointmentId=" + appointmentId +
                ", patientName=" + patientName +
                ", doctorName=" + doctorName +
                ", appointmentDate=" + appointmentDate +
                ", appointmentTime=" + appointmentTime +
                '}';
    }
}
