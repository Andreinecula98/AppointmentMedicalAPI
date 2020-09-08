package com.ApiTheBest.AppointmentAPI.controller.patientController;

import com.ApiTheBest.AppointmentAPI.model.errorModel.ErrorResponse;
import com.ApiTheBest.AppointmentAPI.model.patientModel.addPatient.AddPatientRequest;
import com.ApiTheBest.AppointmentAPI.model.patientModel.addPatient.AddPatientResponse;
import com.ApiTheBest.AppointmentAPI.model.patientModel.getPatient.GetPatientResponse;
import com.ApiTheBest.AppointmentAPI.model.patientModel.updatePatient.UpdatePatientRequest;
import com.ApiTheBest.AppointmentAPI.model.patientModel.updatePatient.UpdatePatientResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Optional;


@Api(value = "display / create",
        description = "REST API for Patient")
public interface PatientController {

    @GetMapping("/displayAll")
    GetPatientResponse getAllPatients(HttpServletResponse httpServletResponse);

    @ApiOperation(value = "Get Customer", nickname = "getCustomer")
    @ApiResponses(value = {
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "Result matching criteria", response = GetPatientResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "No entries found", response = GetPatientResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "Invalid request", response = GetPatientResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_METHOD_NOT_ALLOWED, message = "Method Not Allowed", response = ErrorResponse.class)
    })

    @GetMapping("/display")
    GetPatientResponse getPatients(@RequestParam Optional<Integer> patientId,
                                   @RequestParam Optional<String> phoneNumber,
                                   HttpServletResponse httpServletResponse);

    @PostMapping("/create")
    AddPatientResponse addPatient(@RequestBody @Valid AddPatientRequest addPatientRequest,
                                   HttpServletResponse response);

    @PutMapping("/update")
    UpdatePatientResponse updatePatient(@RequestBody @Valid UpdatePatientRequest updatePatientRequest);
}

