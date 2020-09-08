package com.ApiTheBest.AppointmentAPI.model.patientModel.addPatient;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import javax.validation.constraints.*;

@AllArgsConstructor
@NoArgsConstructor
@Data

/**
 * the customer inserted into DB
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "patientName",
        "address",
        "email",
        "phoneNo"
})
@Getter
@Setter
public class AddPatient {

    /**
     * Name of the patient.
     */
    @JsonProperty("patientName")
    @JsonPropertyDescription(" Name of the patient.")
    @NotNull
    @Size(min = 3, message = "Invalid name")
    private String patientName;

    /**
     * Address of the patient.
     */
    @JsonProperty("address")
    @JsonPropertyDescription(" Address of the patient.")
    @NotNull
    private String address;

    /**
     * Email of the patient.
     */
    @JsonProperty("email")
    @JsonPropertyDescription(" Email of the patient.")
    @NotNull
    private String email;

    /**
     * The phone number of the patient.
     */
    @JsonProperty("phoneNo")
    @JsonPropertyDescription(" The phone number of the doctor. ")
    @Size(min = 9, max = 13, message = "Phone no should be from 2 and 34 digits maxim")
    @Pattern(regexp = "(\\+40|0)[0-9]{9}")
    private String phoneNo;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * Name of the patient.
     */
  /*  @JsonProperty("patientName")
    public String getPatientName() {
        return patientName;
    }

    *//**
     * Name of the patient.
     *//*
    @JsonProperty("patientName")
    public void setPatientName(String Name) {
        this.patientName = patientName;
    }


    *//**
     * Address of the patient.
     *//*
    @JsonProperty("address")
    public String getPatientAddress() {
        return address;
    }

    *//**
     * Address of the patient.
     *//*
    @JsonProperty("address")
    public void setPatientAddress(String Address) {
        this.address = address;
    }

    *//**
     * Email of the patient.
     *//*
    @JsonProperty("email")
    public String getPatientEmail() {
        return email;
    }

    *//**
     * Email of the patient.
     *//*
    @JsonProperty("email")
    public void setPatientEmail(String email) {
        this.email = email;
    }

    *//**
     * The phone number of the patient.
     *//*
    @JsonProperty("phoneNo")
    public String getPhoneNo() {
        return phoneNo;
    }

    *//**
     * The phone number of the patient.
     *//*
    @JsonProperty("phoneNo")
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
*/
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
        return "AddPatient{" +
                "patientName='" + patientName + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}