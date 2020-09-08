package com.ApiTheBest.AppointmentAPI.model.doctorModel.getDoctor;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL) //if a field is null, it will not show in JSON
@JsonPropertyOrder({
        "doctorId",
        "doctorName",
        "phoneNo",
        "contract",
        "medicalSpeciality"
})
@Getter
@Setter

public class GetDoctor {

    /**
     * Doctor id.
     */
    @JsonProperty("doctorId")
    @JsonPropertyDescription(" CDoctor id")
    private Integer doctorId;
    /**
     * Name of the customer.
     */
    @JsonProperty("doctorName")
    @JsonPropertyDescription(" Name of the doctor.")
    private String doctorName;
    /**
     * The phone number of the doctor.
     */
    @JsonProperty("phoneNo")
    @JsonPropertyDescription(" The phone number of the doctor. ")
    private String phoneNo;

    @JsonProperty("contract")
    @JsonPropertyDescription(" Type of contract")
    private String contract;

    @JsonProperty("medicalSpeciality")
    @JsonPropertyDescription("Description")
    private String medicalSpeciality;

    @Override
    public String toString() {
        return "GetDoctor{" +
                "doctorId=" + doctorId +
                ", doctorName='" + doctorName + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", contract='" + contract + '\'' +
                ", medicalSpeciality='" + medicalSpeciality + '\'' +
                '}';
    }
}
