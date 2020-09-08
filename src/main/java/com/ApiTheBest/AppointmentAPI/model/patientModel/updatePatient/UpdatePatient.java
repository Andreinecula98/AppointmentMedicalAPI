package com.ApiTheBest.AppointmentAPI.model.patientModel.updatePatient;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.validation.constraints.*;


/**
 * the patient updated into DB
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "patientId",
        "patientName",
        "address",
        "email",
        "phoneNo"
})

public class UpdatePatient {

    /**
     * Patient id.
     */
    @JsonProperty("patientId")
    @JsonPropertyDescription(" Patient id.")
    private Integer patientId;
    /**
     * Name of the patient.
     */
    @JsonProperty("patientName")
    @JsonPropertyDescription(" Name of the patient.")
    @NotNull
    @Size(min = 3, message = "Invalid name")
    @Pattern(regexp = "[a-zA-Z ]*")
    private String patientName;
    /**
     * The patient address.
     */
    @JsonProperty("address")
    @JsonPropertyDescription(" The patient address.")
    private String address;
    /**
     * The patient email.
     */
    @JsonProperty("email")
    @JsonPropertyDescription(" The patient email.")
    @NotNull
    @Size(min = 8)
    @Email(message = "Wrong type of email!")
    private String email;
    /**
     * The phone number of the patient.
     */
    @JsonProperty("phoneNo")
    @JsonPropertyDescription(" The phone number of the patient. ")
    @NotNull
    @Pattern(regexp = "(\\+40|0)[0-9]{9}")
    private String phoneNo;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * Patient id.
     */
    @JsonProperty("patientId")
    public Integer getPatientId() {
        return patientId;
    }

    /**
     * Patient id.
     */
    @JsonProperty("patientId")
    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    /**
     * Name of the patient.
     */
    @JsonProperty("patientName")
    public String getPatientName() {
        return patientName;
    }

    /**
     * Name of the patient.
     */
    @JsonProperty("patientName")
    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    /**
     * The patient address.
     */
    @JsonProperty("address")
    public String getAddress() {
        return address;
    }

    /**
     * The patient address.
     */
    @JsonProperty("address")
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * The patient email.
     */
    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    /**
     * The patient email.
     */
    @JsonProperty("email")
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * The phone number of the patient.
     */
    @JsonProperty("phoneNo")
    public String getPhoneNo() {
        return phoneNo;
    }

    /**
     * The phone number of the patient.
     */
    @JsonProperty("phoneNo")
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

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
        return "UpdatePatient{" +
                "patientId=" + patientId +
                ", patientName='" + patientName + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}
