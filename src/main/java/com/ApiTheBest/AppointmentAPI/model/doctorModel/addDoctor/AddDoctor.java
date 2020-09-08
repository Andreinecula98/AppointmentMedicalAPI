package com.ApiTheBest.AppointmentAPI.model.doctorModel.addDoctor;


import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;


@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "doctorName",
        "phoneNo",
        "contract",
        "medicalSpeciality"
})
public class AddDoctor {

    /**
     * Name of the doctor.
     */
    @JsonProperty("doctorName")
    @JsonPropertyDescription(" Name of the doctor.")
    @NotNull
    @Size(min = 5, message = "Invalid name")
    @Pattern(regexp = "[a-zA-Z ]*")
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
    private String contract;
    /**
     * Medic Speciality.
     */
    @JsonProperty("medicalSpeciality")
    @JsonPropertyDescription(" Medic Speciality.")
    @NotNull
    private String medicalSpeciality;



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


    @Override
    public String toString() {
        return "AddDoctor{" +
                "doctorName='" + doctorName + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}
