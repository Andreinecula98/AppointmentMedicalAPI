package com.ApiTheBest.AppointmentAPI.model.appointmentModel.getAppointment;


import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * getAppointmentRsp
 * <p>
 * get appointment structure, stored in DB.
 *
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL) //if a field is null, it will not show in JSON
@JsonPropertyOrder({
        "appointment",
        "responseDescription"
})

public class GetAppointmentResponse {
    @JsonProperty("appointment")
    private ArrayList<GetAppointment> getAppointment;
    /**
     * Description of the status.
     *
     */
    @JsonProperty("responseDescription")
    @JsonPropertyDescription(" Description of the status.")
    private String responseDescription;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public ArrayList<GetAppointment> getGetAppointment() {
        return getAppointment;
    }

    public void setGetCustomer(ArrayList<GetAppointment> getAppointment) {
        this.getAppointment = new ArrayList<>();
        this.getAppointment = getAppointment;
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
        return "GetAppointmentResponse{" +
                "getAppointment=" + getAppointment +
                ", responseDescription='" + responseDescription + '\'' +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}
