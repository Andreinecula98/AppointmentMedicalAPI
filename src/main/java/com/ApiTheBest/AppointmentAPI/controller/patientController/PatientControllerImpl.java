package com.ApiTheBest.AppointmentAPI.controller.patientController;

import com.ApiTheBest.AppointmentAPI.controller.Lists;
import com.ApiTheBest.AppointmentAPI.model.patientModel.Patient;
import com.ApiTheBest.AppointmentAPI.model.patientModel.addPatient.AddPatient;
import com.ApiTheBest.AppointmentAPI.model.patientModel.addPatient.AddPatientRequest;
import com.ApiTheBest.AppointmentAPI.model.patientModel.addPatient.AddPatientResponse;
import com.ApiTheBest.AppointmentAPI.model.patientModel.getPatient.GetPatient;
import com.ApiTheBest.AppointmentAPI.model.patientModel.getPatient.GetPatientResponse;
import com.ApiTheBest.AppointmentAPI.model.patientModel.updatePatient.UpdatePatient;
import com.ApiTheBest.AppointmentAPI.model.patientModel.updatePatient.UpdatePatientRequest;
import com.ApiTheBest.AppointmentAPI.model.patientModel.updatePatient.UpdatePatientResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/patient")
public class PatientControllerImpl implements PatientController {

    private Logger log = LoggerFactory.getLogger(PatientControllerImpl.class);

    private static List<Patient> patients = new ArrayList<>();

    private static Integer patientIdValue = 4;

    static{
        patients = Lists.getPatients();
    }

    @Override
    public GetPatientResponse getAllPatients(HttpServletResponse httpServletResponse) {

        ArrayList<GetPatient> responseList = new ArrayList<>();

        for(Patient patient: patients){
            //if doctor was found, send back response
            GetPatient response = new GetPatient();
            //preparing response
            response.setPatientId(patient.getPatientId());
            response.setPatientName(patient.getPatientName());
            response.setPhoneNo(patient.getPhoneNo());
            response.setEmail(patient.getEmail());
            response.setAddress(patient.getAddress());


            //add to list
            log.info("Patient found and added to the list");
            log.debug("Patient found and added " + response.toString());
            responseList.add(response);
        }

        if(responseList.isEmpty()){
            //no customers found
            httpServletResponse.setStatus(404);
            GetPatientResponse response = new GetPatientResponse();
            response.setResponseDescription("No entries found");
            return response;
        }
        //if there are customers, send 200
        else {
            httpServletResponse.setStatus(200);
            GetPatientResponse response = new GetPatientResponse();
            response.setGetPatient(responseList);
            response.setResponseDescription("Result matching criteria");
            return response;
        }
    }

    @Override
    public GetPatientResponse getPatients(Optional<Integer> patientId,
                                          Optional<String> phoneNumber,
                                          HttpServletResponse httpServletResponse) {


        log.info("Called /getPatient");
        log.trace("/Called /getPatient with patientId= " + patientId + " and phoneNumber = "+ phoneNumber);
        log.debug("Called /getPatient at "+ LocalDate.now());
        //log.info(System.getProperty("user.dir"));

        // only patientId is passed
        if (patientId.isPresent() && phoneNumber.isEmpty()) {
            log.info("patientId is present");
            log.debug("patientId= " + patientId.get());
            //processing
            // create response list
            ArrayList<GetPatient> responseList = new ArrayList<>();
            //patientId is found in the list
            for (Patient patient : patients) {

                //patient found, send back response
                if (patient.getPatientId().equals(patientId.get())) {
                    GetPatient response = new GetPatient();
                    //preparing response
                    response.setPatientId(patientId.get());
                    response.setPatientName(patient.getPatientName());
                    response.setAddress(patient.getAddress());
                    response.setEmail(patient.getEmail());
                    response.setPhoneNo(patient.getPhoneNo());
                    log.info("Patient found and added to the list ");
                    log.debug("Patient found and added: "+ response.toString());
                    //add to list
                    responseList.add(response);
                }
            }
            //check if there any patients in the list
            if (responseList.isEmpty()) {
                log.info("No patients found");
                log.debug("No patients found with the patientId = " + patientId.get());
                //no patients found
                httpServletResponse.setStatus(404);
                GetPatientResponse response = new GetPatientResponse();
                response.setResponseDescription("No entries found");
                return response;
            }
            //if there are patients, send 200
            else {
                log.info("Returning list of patients");
                log.debug("Returning number of " + responseList.size()+ " patients");
                httpServletResponse.setStatus(200);
                GetPatientResponse response = new GetPatientResponse();
                response.setGetPatient(responseList);
                response.setResponseDescription("Result matching criteria");
                return response;
            }
        }
        // just phoneNumber is passed as argument
        else if (patientId.isEmpty() && phoneNumber.isPresent()) {
            //processing
            // create response list
            ArrayList<GetPatient> responseList = new ArrayList<>();
            //patientId is found in the list
            for (Patient patient : patients) {

                //patient found, send back response
                if (patient.getPhoneNo().equals(phoneNumber.get())) {
                    GetPatient response = new GetPatient();
                    //preparing response
                    response.setPatientId(patient.getPatientId());
                    response.setPatientName(patient.getPatientName());
                    response.setAddress(patient.getAddress());
                    response.setEmail(patient.getEmail());
                    response.setPhoneNo(phoneNumber.get());
                    //add to list
                    responseList.add(response);
                }
            }
            //check if there any patients
            if (responseList.isEmpty()) {
                //no patients found
                httpServletResponse.setStatus(404);
                GetPatientResponse response = new GetPatientResponse();
                response.setResponseDescription("No entries found");
                return response;
            }
            //if there are patients, send 200
            else {
                httpServletResponse.setStatus(200);
                GetPatientResponse response = new GetPatientResponse();
                response.setGetPatient(responseList);
                response.setResponseDescription("Result matching criteria");
                return response;
            }
        }
        //error case
        else {
            //processing
            httpServletResponse.setStatus(400); //specify that request is invalid
            GetPatientResponse response = new GetPatientResponse();
            response.setResponseDescription("Invalid request");
            return response;

        }
    }

    @Override
    public AddPatientResponse addPatient(AddPatientRequest addPatientRequest, HttpServletResponse response) {
        AddPatient addPatient = addPatientRequest.getPatient();
        //check if the patient already exists
        for (Patient patient : patients) {
            //if patient already exists, return duplicate status
            if (checkAddPatient(addPatient, patient)) {
//                response.setStatus(409);
                response.setStatus(HttpServletResponse.SC_CONFLICT);
                AddPatientResponse addResponse = new AddPatientResponse();
                addResponse.setResponseDescription("Patient already exists");
                return addResponse;
            }
        }
        //if not, add them to the list
        Patient patient = new Patient();
        patient.setPatientId(patientIdValue);

        patient.setPatientName(addPatient.getPatientName());
        patient.setAddress(addPatient.getAddress());
        patient.setEmail(addPatient.getEmail());
        patient.setPhoneNo(addPatient.getPhoneNo());

        patients.add(patient);
        Lists.setPatients(patients);
        //send success response
        response.setStatus(HttpServletResponse.SC_CREATED);
        AddPatientResponse addResponse = new AddPatientResponse();
        addResponse.setPatientId(patientIdValue);
        patientIdValue++;
        addResponse.setResponseDescription("Patient added");
        return addResponse;

    }

    @Override
    public UpdatePatientResponse updatePatient(@Valid UpdatePatientRequest updatePatientRequest) {
        //find patient in the list
        log.info("Called /updatePatient");
        log.trace("Called /updatePatient with request: " + updatePatientRequest.getPatient());
        log.debug("Called /updatePatient at " + LocalDate.now());
        UpdatePatient updatePatient = updatePatientRequest.getPatient();
        //if found, make sure that the request is not identical to the entry
        for( Patient patient: patients ){
            if(updatePatient.getPatientId().equals(patient.getPatientId())) {
                if (checkUpdate(updatePatient, patient)) {
                    //if identical, return error message
                    log.info("Same Request");
                    log.trace("Patient = " + updatePatient.getPatientName());
                    UpdatePatientResponse response = new UpdatePatientResponse();
                    response.setResponseDescription("Request identical with entry");
                    return response;
                } else {
                    //if not identical, make update
                    patient.setPatientName(updatePatient.getPatientName());
                    patient.setAddress(updatePatient.getAddress());
                    patient.setEmail(updatePatient.getEmail());
                    patient.setPhoneNo(updatePatient.getPhoneNo());

                    UpdatePatientResponse response = new UpdatePatientResponse();
                    response.setResponseDescription("Item updated");
                    log.info("Patient updated successful");
                    log.debug("Patient found and updated : " + response.toString());
                    return response;
                }
            }
        }
        //Lists.setPatients(patients);
        //in case customer was not found, return 404
        UpdatePatientResponse response = new UpdatePatientResponse();
        response.setResponseDescription("Customer with id" + updatePatient.getPatientId() + "was not found!");
        log.info("No customers found");
        log.debug("No customers found with customerDates: " + updatePatientRequest.getPatient());
        return response;
    }

    private boolean checkAddPatient(AddPatient addPatient, Patient patient) {

        if (addPatient.getPatientName()!=null && patient.getPatientName()!=null){
            if (addPatient.getPatientName().equals(patient.getPatientName())){
                return true;
            }
        }

        return false;
    }

    private boolean checkUpdate(UpdatePatient updatePatient, Patient patient){
        if( updatePatient.getPatientId().equals(patient.getPatientId()) &
                updatePatient.getPatientName().equals(patient.getPatientName())){
            return true;
        }else
            return false;

    }

}