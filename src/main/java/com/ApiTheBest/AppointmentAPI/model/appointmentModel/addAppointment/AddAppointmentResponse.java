package com.ApiTheBest.AppointmentAPI.model.appointmentModel.addAppointment;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * the appointment inserted into DB
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "appointmentId",
        "responseDescription"
})
public class AddAppointmentResponse {
    /**
     * The id of the new created customer.
     *
     */
    @JsonProperty("appointmentId")
    @JsonPropertyDescription(" The id of the new created appointment.")
    private Integer appointmentId;
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
     * The id of the new created customer.
     *
     */
    @JsonProperty("appointmentId")
    public Integer getAppointmentId() {
        return appointmentId;
    }

    /**
     * The id of the new created customer.
     *
     */
    @JsonProperty("appointmentId")
    public void setAppointmentId(Integer appointmentId) {
        this.appointmentId = appointmentId;
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
        return "AddAppointmentResponse{" +
                "appointmentId=" + appointmentId +
                ", responseDescription='" + responseDescription + '\'' +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}
