package com.ApiTheBest.AppointmentAPI.model.patientModel.addPatient;


import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * addPacientRsp
 * <p>
 * response after inserting a new customer structure into DB.
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "patientId",
        "responseDescription"
})
public class AddPatientResponse {

    /**
     * The id of the new created patient.
     *
     */
    @JsonProperty("patientId")
    @JsonPropertyDescription(" The id of the new created patient.")
    private Integer patientId;
    /**
     * Description of the status.
     *
     */
    @JsonProperty("responseDescription")
    @JsonPropertyDescription(" Description of the status.")
    private String responseDescription;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * The id of the new created patient.
     *
     */
    @JsonProperty("patientId")
    public Integer getPatientId() {
        return patientId;
    }

    /**
     * The id of the new created customer.
     *
     */
    @JsonProperty("patientId")
    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    /**
     * Description of the status.
     *
     */
    @JsonProperty("responseDescription")
    public String getResponseDescription() {
        return responseDescription;
    }

    /**
     * Description of the status.
     *
     */
    @JsonProperty("responseDescription")
    public void setResponseDescription(String responseDescription) {
        this.responseDescription = responseDescription;
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
        return "AddPatientResponse{" +
                "patientId=" + patientId +
                ", responseDescription='" + responseDescription + '\'' +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}
