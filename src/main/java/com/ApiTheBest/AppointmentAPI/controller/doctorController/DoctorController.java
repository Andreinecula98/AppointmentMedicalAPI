package com.ApiTheBest.AppointmentAPI.controller.doctorController;

import com.ApiTheBest.AppointmentAPI.model.doctorModel.addDoctor.AddDoctorRequest;
import com.ApiTheBest.AppointmentAPI.model.doctorModel.addDoctor.AddDoctorResponse;
import com.ApiTheBest.AppointmentAPI.model.doctorModel.getDoctor.GetDoctorResponse;
import com.ApiTheBest.AppointmentAPI.model.doctorModel.updateDoctor.UpdateDoctorRequest;
import com.ApiTheBest.AppointmentAPI.model.doctorModel.updateDoctor.UpdateDoctorResponse;
import com.ApiTheBest.AppointmentAPI.model.errorModel.ErrorResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Optional;

@Api(value = "display / create",
        description = "REST API for Doctor")
public interface DoctorController {

    @GetMapping("/displayAll")
    GetDoctorResponse displayAllDoctors(HttpServletResponse httpServletResponse);

    @ApiOperation(value = "Get Doctor", nickname = "display")
    @ApiResponses(value = {
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "Result matching criteria", response = GetDoctorResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "No entries found", response = GetDoctorResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "Invalid request", response = GetDoctorResponse.class),
            @ApiResponse(code = HttpServletResponse.SC_METHOD_NOT_ALLOWED, message = "Method Not Allowed", response = ErrorResponse.class)
    })

    @GetMapping("/display")
    GetDoctorResponse displayDoctor(@RequestParam Optional<Integer> doctorId,
                                            @RequestParam Optional<String> doctorName,
                                            HttpServletResponse httpServletResponse);

    @PostMapping("/create")
    AddDoctorResponse addDoctor(@RequestBody @Valid AddDoctorRequest addDoctorRequest,
                                HttpServletResponse response);

    @PutMapping("/update")
    UpdateDoctorResponse updateDoctor(@RequestBody @Valid UpdateDoctorRequest updateDoctorRequest);
}
