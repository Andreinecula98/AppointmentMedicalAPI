package com.ApiTheBest.AppointmentAPI.model.doctorModel.updateDoctor;


import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import javax.validation.constraints.*;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter



@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "doctorId",
        "doctorName",
        "phoneNo",
        "contract",
        "medicalSpeciality"
})

public class UpdateDoctor {

    /**
     * Name of the doctor.
     */
    @JsonProperty("doctorId")
    @JsonPropertyDescription(" Id of the doctor")
    private Integer doctorId;
    /**
     * Name of the doctor.
     */
    @JsonProperty("doctorName")
    @JsonPropertyDescription(" Name of the doctor.")
    @NotNull
    @Size(min = 4, message = "Invalid name")
    private String doctorName;
    /**
     * The phone number of the doctor.
     */
    @JsonProperty("phoneNo")
    @JsonPropertyDescription(" The phone number of the doctor. ")
    @Size(min = 9, max = 13, message = "Phone no should be from 2 and 34 digits maxim")
    @Pattern(regexp = "(\\+40|0)[0-9]{9}")
    private String phoneNo;

    /**
     * Type of contract.
     */
    @JsonProperty("contract")
    @JsonPropertyDescription(" Type of contract")
    @NotNull
// sa nu uiti ma mamaie ma sa scrii pattern pt full time/part time
    private String contract;
    /**
     * Medic Speciality.
     */
    @JsonProperty("medicalSpeciality")
    @JsonPropertyDescription(" Medic Speciality.")
    @NotNull
// validari de facut
    private String medicalSpeciality;


    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @Override
    public String toString() {
        return "UpdateDoctor{" +
                "doctorName='" + doctorName + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", contract='" + contract + '\'' +
                ", medicalSpeciality='" + medicalSpeciality + '\'' +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}
