package com.ApiTheBest.AppointmentAPI.controller.doctorController;

import com.ApiTheBest.AppointmentAPI.controller.Lists;
import com.ApiTheBest.AppointmentAPI.model.doctorModel.Doctor;
import com.ApiTheBest.AppointmentAPI.model.doctorModel.addDoctor.AddDoctor;
import com.ApiTheBest.AppointmentAPI.model.doctorModel.addDoctor.AddDoctorRequest;
import com.ApiTheBest.AppointmentAPI.model.doctorModel.addDoctor.AddDoctorResponse;
import com.ApiTheBest.AppointmentAPI.model.doctorModel.getDoctor.GetDoctor;
import com.ApiTheBest.AppointmentAPI.model.doctorModel.getDoctor.GetDoctorResponse;
import com.ApiTheBest.AppointmentAPI.model.doctorModel.updateDoctor.UpdateDoctor;
import com.ApiTheBest.AppointmentAPI.model.doctorModel.updateDoctor.UpdateDoctorRequest;
import com.ApiTheBest.AppointmentAPI.model.doctorModel.updateDoctor.UpdateDoctorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/doctor")
@Slf4j
public class DoctorControllerImpl implements DoctorController{

    private static List<Doctor> doctors = new ArrayList<>();
    private static Integer doctorIdValue  = 3;

    static{
        doctors = Lists.getDoctors();
    }

    @Override
    public GetDoctorResponse displayAllDoctors(HttpServletResponse httpServletResponse) {

        ArrayList<GetDoctor> responseList = new ArrayList<>();
        for(Doctor doctor: doctors){
            //if doctor was found, send back response
                GetDoctor response = new GetDoctor();
                //preparing response
                response.setDoctorId(doctor.getDoctorId());
                response.setDoctorName(doctor.getDoctorName());
                response.setPhoneNo(doctor.getPhoneNo());
                response.setContract(doctor.getContract());
                response.setMedicalSpeciality(doctor.getMedicalSpeciality());

                //add to list
                log.info("Doctor found and added to the list");
                log.debug("Doctor found and added " + response.toString());
                responseList.add(response);
        }

        if(responseList.isEmpty()){
            //no customers found
            httpServletResponse.setStatus(404);
            GetDoctorResponse response = new GetDoctorResponse();
            response.setResponseDescription("No entries found");
            return response;
        }
        //if there are customers, send 200
        else {
            httpServletResponse.setStatus(200);
            GetDoctorResponse response = new GetDoctorResponse();
            response.setGetDoctor(responseList);
            response.setResponseDescription("Result matching criteria");
            return response;
        }

    }

    @Override
    public GetDoctorResponse displayDoctor(Optional<Integer> doctorId, Optional<String> doctorName, HttpServletResponse httpServletResponse) {

        //create a response list with doctors
        ArrayList<GetDoctor> responseList = new ArrayList<>();

        log.info("Called: doctor/display");
        log.trace("Called doctor/display with doctorId " + doctorId + " " + "and name" + doctorName);
        log.debug("Called doctor/display at " + LocalDate.now());

        if(doctorId.isPresent() && doctorName.isEmpty()){
            log.info("doctorId is present");
            log.debug("doctorId = " + doctorId);

            for(Doctor doctor: doctors){
                //if doctor was found, send back response
                if(doctor.getDoctorId().equals(doctorId.get())){
                    GetDoctor response = new GetDoctor();
                    //preparing response
                    response.setDoctorId(doctorId.get());
                    response.setDoctorName(doctor.getDoctorName());
                    response.setPhoneNo(doctor.getPhoneNo());
                    response.setContract(doctor.getContract());
                    response.setMedicalSpeciality(doctor.getMedicalSpeciality());

                    //add to list
                    log.info("Doctor found and added to the list");
                    log.debug("Doctor found and added " + response.toString());
                    responseList.add(response);
                }
            }
        }
        else if(doctorId.isEmpty() && doctorName.isPresent()){
            log.info("doctorName is present");
            log.debug("doctorName = " + doctorName);

            for(Doctor doctor: doctors) {
                if(doctor.getDoctorName().equals(doctorName.get())){
                    GetDoctor response = new GetDoctor();
                    //preparing response
                    response.setDoctorId(doctor.getDoctorId());
                    response.setDoctorName(doctorName.get());
                    response.setPhoneNo(doctor.getPhoneNo());
                    response.setContract(doctor.getContract());
                    response.setMedicalSpeciality(doctor.getMedicalSpeciality());

                    //add to list
                    log.info("Doctor found and added to the list");
                    log.debug("Doctor found and added " + response.toString());
                    responseList.add(response);
                }
            }
        }
        else{
            //processing
            httpServletResponse.setStatus(400);
            GetDoctorResponse response = new GetDoctorResponse();
            response.setResponseDescription("Invalid request");
            return response;

        }
        //check if there any doctors
        if(responseList.isEmpty()){
            //no customers found
            httpServletResponse.setStatus(404);
            GetDoctorResponse response = new GetDoctorResponse();
            response.setResponseDescription("No entries found");
            return response;
        }
        //if there are customers, send 200
        else {
            httpServletResponse.setStatus(200);
            GetDoctorResponse response = new GetDoctorResponse();
            response.setGetDoctor(responseList);
            response.setResponseDescription("Result matching criteria");
            return response;
        }
    }

    @Override
    public AddDoctorResponse addDoctor(@Valid AddDoctorRequest addDoctorRequest, HttpServletResponse response) {

        AddDoctor addDoctor = addDoctorRequest.getDoctor();

        //check if doctor already exists
        for(Doctor doctor: doctors){
            if(checkAddDoctor(doctor, addDoctor)){
                log.info("Doctor already exists!");
                log.trace("Doctor with name " + addDoctor.getDoctorName() + ", " + "Phone Number: " +
                        addDoctor.getPhoneNo());
                response.setStatus(HttpServletResponse.SC_CONFLICT);
                AddDoctorResponse addResponse = new AddDoctorResponse();
                addResponse.setResponseDescription("Doctor already exists!");
                return addResponse;
            }
        }

        //if not, add him to the list
        log.info("Called /doctor/create");
        log.trace("Called /doctor/create with name " + addDoctor.getDoctorName() + ", " + "Phone Number: " +
                addDoctor.getPhoneNo());
        log.debug("Called /doctor/create at " + LocalDate.now());

        Doctor doctor = new Doctor();
        doctor.setDoctorId(doctorIdValue);

        doctor.setDoctorName(addDoctor.getDoctorName());
        doctor.setContract(addDoctor.getContract());
        doctor.setMedicalSpeciality(addDoctor.getMedicalSpeciality());
        doctor.setPhoneNo(addDoctor.getPhoneNo());

        doctors.add(doctor);
        Lists.setDoctors(doctors);
        //send response
        response.setStatus(HttpServletResponse.SC_CREATED);
        AddDoctorResponse addResponse = new AddDoctorResponse();
        addResponse.setdoctorId(doctorIdValue);
        doctorIdValue++;
        addResponse.setResponseDescription("Doctor added");
        return addResponse;

    }

    @Override
    public UpdateDoctorResponse updateDoctor(@Valid UpdateDoctorRequest updateDoctorRequest) {

        //find patient in the list
        log.info("Called /updateDoctor");
        log.trace("Called /updateDoctor with request: " + updateDoctorRequest.getDoctor());
        log.debug("Called /updatePatient at " + LocalDate.now());
        UpdateDoctor updateDoctor = updateDoctorRequest.getDoctor();
        //if found, make sure that the request is not identical to the entry
        for( Doctor doctor: doctors ){
            if(updateDoctor.getDoctorId().equals(doctor.getDoctorId())) {
                if (checkUpdate(updateDoctor, doctor)) {
                    //if identical, return error message
                    log.info("Same Request");
                    log.trace("Patient = " + updateDoctor.getDoctorName());
                    UpdateDoctorResponse response = new UpdateDoctorResponse();
                    response.setResponseDescription("Request identical with entry");
                    return response;
                } else {
                    //if not identical, make update
                    doctor.setDoctorName(updateDoctor.getDoctorName());
                    doctor.setContract(updateDoctor.getContract());
                    doctor.setMedicalSpeciality(updateDoctor.getMedicalSpeciality());
                    doctor.setPhoneNo(updateDoctor.getPhoneNo());

                    UpdateDoctorResponse response = new UpdateDoctorResponse();
                    response.setResponseDescription("Item updated");
                    log.info("Doctor updated successful");
                    log.debug("Patient found and updated : " + response.toString());
                    return response;
                }
            }
        }
        //in case customer was not found, return 404
        UpdateDoctorResponse response = new UpdateDoctorResponse();
        response.setResponseDescription("Customer with id " + updateDoctor.getDoctorId() + " was not found!");
        log.info("No customers found");
        log.debug("No customers found with customerDates: " + updateDoctorRequest.getDoctor());
        return response;
    }

    private boolean checkAddDoctor(Doctor doctor, AddDoctor addDoctor){
        if(doctor.getDoctorName().equals(addDoctor.getDoctorName()) &&
                doctor.getPhoneNo().equals(addDoctor.getPhoneNo())){
            return true;
        } else {
            return false;
        }
    }

    private boolean checkUpdate(UpdateDoctor updateDoctor, Doctor doctor){
        if(updateDoctor.getDoctorName().equals(doctor.getDoctorName())&&
                updateDoctor.getPhoneNo().equals(doctor.getPhoneNo())){
            return true;
        }else
            return false;

    }

}
