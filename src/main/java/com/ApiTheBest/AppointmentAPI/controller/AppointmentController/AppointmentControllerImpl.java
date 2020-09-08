package com.ApiTheBest.AppointmentAPI.controller.AppointmentController;

import com.ApiTheBest.AppointmentAPI.controller.Lists;
import com.ApiTheBest.AppointmentAPI.model.appointmentModel.Appointment;
import com.ApiTheBest.AppointmentAPI.model.appointmentModel.addAppointment.AddAppointment;
import com.ApiTheBest.AppointmentAPI.model.appointmentModel.addAppointment.AddAppointmentRequest;
import com.ApiTheBest.AppointmentAPI.model.appointmentModel.addAppointment.AddAppointmentResponse;
import com.ApiTheBest.AppointmentAPI.model.appointmentModel.deleteAppointment.DeleteAppointmentResponse;
import com.ApiTheBest.AppointmentAPI.model.appointmentModel.getAppointment.GetAppointment;
import com.ApiTheBest.AppointmentAPI.model.appointmentModel.getAppointment.GetAppointmentResponse;
import com.ApiTheBest.AppointmentAPI.model.appointmentModel.updateAppointment.UpdateAppointment;
import com.ApiTheBest.AppointmentAPI.model.appointmentModel.updateAppointment.UpdateAppointmentRequest;
import com.ApiTheBest.AppointmentAPI.model.appointmentModel.updateAppointment.UpdateAppointmentResponse;
import com.ApiTheBest.AppointmentAPI.model.doctorModel.Doctor;
import com.ApiTheBest.AppointmentAPI.model.errorModel.ErrorResponse;
import com.ApiTheBest.AppointmentAPI.model.patientModel.Patient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/appointment")
@Slf4j

public class AppointmentControllerImpl implements AppointmentController {

    private static List<Appointment> appointments = new ArrayList<>();
    private static Integer appointmentIdValue = 2;
    private static List<Doctor> doctors = new ArrayList<>();
    private static List<Patient> patients = new ArrayList<>();

    static {

        doctors = Lists.getDoctors();
        patients = Lists.getPatients();

        appointments.add(new Appointment(1, patients.get(0).getPatientName(), doctors.get(0).getDoctorName(),
                LocalDate.of(2020, 07, 12),
                LocalTime.now()));
    }

    @Override
    public GetAppointmentResponse displayAllAppointment(HttpServletResponse httpServletResponse) {

        ArrayList<GetAppointment> responseList = new ArrayList<>();
        for(Appointment appointment: appointments){
            //if doctor was found, send back response
            GetAppointment response = new GetAppointment();
            //preparing response
            response.setAppointmentId(appointment.getAppointmentId());
            response.setDoctorName(appointment.getDoctorName());
            response.setPatientName(appointment.getPatientName());
            response.setAppointmentDate(appointment.getAppointmentDate());
            response.setAppointmentTime(appointment.getAppointmentTime());

            //add to list
            log.info("Appointment found and added to the list");
            log.debug("Appointment found and added " + response.toString());
            responseList.add(response);
        }

        if(responseList.isEmpty()){
            //no customers found
            httpServletResponse.setStatus(404);
            GetAppointmentResponse response = new GetAppointmentResponse();
            response.setResponseDescription("No entries found");
            return response;
        }
        //if there are customers, send 200
        else {
            httpServletResponse.setStatus(200);
            GetAppointmentResponse response = new GetAppointmentResponse();
            response.setGetAppointment(responseList);
            response.setResponseDescription("Result matching criteria");
            return response;
        }
    }

    @Override
    public GetAppointmentResponse displayAppointment(Optional<Integer> appointmentId, Optional<LocalDate> appointmentDate, HttpServletResponse httpServletResponse) {
        //create a response list with doctors
        ArrayList<GetAppointment> responseList = new ArrayList<>();

        log.info("Called: appointment/display");
        log.trace("Called appointment/display with doctorId " + appointmentId + " " + "and the date" + appointmentDate);
        log.debug("Called appointment/display at " + LocalDate.now());

        if (appointmentId.isPresent() && appointmentDate.isEmpty()) {
            log.info("appointmentId is present");
            log.debug("appointmentId = " + appointmentId);

            for (Appointment appointment : appointments) {
                //if doctor was found, send back response
                if (appointment.getAppointmentId().equals(appointmentId.get())) {
                    GetAppointment response = new GetAppointment();
                    //preparing response
                    response.setAppointmentId(appointmentId.get());
                    response.setPatientName(appointment.getPatientName());
                    response.setDoctorName(appointment.getDoctorName());
                    response.setAppointmentDate(appointment.getAppointmentDate());
                    response.setAppointmentTime(appointment.getAppointmentTime());

//                    //add to list
                    log.info("Appointment found and added to the list");
                    log.debug("Appointment found and added " + response.toString());
                    responseList.add(response);
                }
            }
        } else if (appointmentId.isEmpty() && appointmentDate.isPresent()) {
            log.info("appointmentDate is present");
            log.debug("appointmentDate = " + appointmentDate);

            for (Appointment appointment : appointments) {
                if (appointment.getAppointmentDate().equals(appointmentDate)) {
                    GetAppointment response = new GetAppointment();
                    //preparing response
                    response.setAppointmentId(appointment.getAppointmentId());
                    response.setPatientName(appointment.getPatientName());
                    response.setDoctorName(appointment.getDoctorName());
                    response.setAppointmentDate(appointmentDate.get());
                    response.setAppointmentTime(appointment.getAppointmentTime());

                    //add to list
                    log.info("Appointment found and added to the list");
                    log.debug("Appointment found and added " + response.toString());
                    responseList.add(response);
                }
            }
        } else {
            //processing
            httpServletResponse.setStatus(400);
            GetAppointmentResponse response = new GetAppointmentResponse();
            response.setResponseDescription("Invalid request");
            return response;

        }
        //check if there any doctors
        if (responseList.isEmpty()) {
            //no customers found
            httpServletResponse.setStatus(404);
            GetAppointmentResponse response = new GetAppointmentResponse();
            response.setResponseDescription("No entries found");
            return response;
        }
        //if there are customers, send 200
        else {
            httpServletResponse.setStatus(200);
            GetAppointmentResponse response = new GetAppointmentResponse();
            response.setGetAppointment(responseList);
            response.setResponseDescription("Result matching criteria");
            return response;
        }
    }


    @Override
    public AddAppointmentResponse addAppointment(@Valid AddAppointmentRequest addAppointmentRequest, HttpServletResponse response) {

        AddAppointment addAppointment = addAppointmentRequest.getAppointment();

        boolean checkDoctor = false;
        boolean checkPatient = false;
        //check if doctor already exists
        for (Doctor doctor : doctors) {
            if (doctor.getDoctorName().equals(addAppointment.getDoctorName())) {
                checkDoctor = true;
            }
        }
        for (Patient patient : patients) {
            if (patient.getPatientName().equals(addAppointment.getPatientName())) {
                checkPatient = true;
            }
        }

        if (checkDoctor == false && checkPatient == false) {
            log.info("Doctor and patient not exists!");
            log.trace("Doctor with doctor name " + addAppointment.getDoctorName() + " and patient with name: " + addAppointment.getPatientName());
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            AddAppointmentResponse addResponse = new AddAppointmentResponse();
            addResponse.setResponseDescription("Doctor and patient do not exist in the lists!");
            return addResponse;
        } else if (checkPatient == false) {
            log.info("Patient not exists!");
            log.trace("Patient with patient name " + addAppointment.getPatientName());
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            AddAppointmentResponse addResponse = new AddAppointmentResponse();
            addResponse.setResponseDescription("Patient does not exist in the list, please register!");
            return addResponse;
        } else if (checkDoctor == false) {
            log.info("Doctor not exists!");
            log.trace("Doctor with doctor name " + addAppointment.getDoctorName());
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            AddAppointmentResponse addResponse = new AddAppointmentResponse();
            addResponse.setResponseDescription("Doctor does not exist in the list, we don't have this doctor in the Hospital!");
            return addResponse;
        }

        //check if appointment already exists
        for (Appointment appointment : appointments) {
            if (checkAddAppointment(appointment, addAppointment)) {
                log.info("Appointment already exists!");
                log.trace("Appointment with doctor name " + addAppointment.getDoctorName() + ", " + "with Patient name: " +
                        addAppointment.getPatientName() + ", " + "with Date : " + addAppointment.getAppointmentDate());
                response.setStatus(HttpServletResponse.SC_CONFLICT);
                AddAppointmentResponse addResponse = new AddAppointmentResponse();
                addResponse.setResponseDescription("Appointment already exists!");
                return addResponse;
            }
        }

        //check if appointmentDate and appointmentTime already exists
        for (Appointment appointment : appointments) {
            if (checkDateTime(appointment, addAppointment)) {
                log.info("Appointment for the chosen doctor, at the specified date and time already exists!");
                response.setStatus(HttpServletResponse.SC_CONFLICT);
                AddAppointmentResponse addResponse = new AddAppointmentResponse();
                addResponse.setResponseDescription("Appointment for the specified date, at the specified date already exists!");
                return addResponse;
            }
        }


        //if not, add him to the list
        log.info("Called /appointment/create");
        log.trace("Called /appointment/create with doctor name " + addAppointment.getDoctorName() + ", " + "with Patient name: " +
                addAppointment.getPatientName() + ", " + "with Date : " + addAppointment.getAppointmentDate());
        log.debug("Called /appointment/create at " + LocalDate.now());

        Appointment appointment = new Appointment();
        appointment.setAppointmentId(appointmentIdValue);

        appointment.setDoctorName(addAppointment.getDoctorName());
        appointment.setPatientName(addAppointment.getPatientName());
        appointment.setAppointmentDate(addAppointment.getAppointmentDate());
        appointment.setAppointmentTime(addAppointment.getAppointmentTime());

        appointments.add(appointment);
        //send response
        response.setStatus(HttpServletResponse.SC_CREATED);
        AddAppointmentResponse addResponse = new AddAppointmentResponse();
        addResponse.setAppointmentId(appointmentIdValue);
        appointmentIdValue++;
        addResponse.setResponseDescription("Appointment added");
        return addResponse;

    }

    @Override
    public ResponseEntity<?> updateAppointment(UpdateAppointmentRequest updateAppointmentRequest) {

        log.info("Called /updateAppointment");
        log.trace("Called /updateAppointment with request: " + updateAppointmentRequest.getAppointment());
        log.debug("Called /updateAppointment at " + LocalDate.now());

        UpdateAppointment updateAppointment = updateAppointmentRequest.getAppointment();

        boolean checkDoctor = false;
        boolean checkPatient = false;
        //check if doctor already exists
        for (Doctor doctor : doctors) {
            if (doctor.getDoctorName().equals(updateAppointment.getDoctorName())) {
                checkDoctor = true;
            }
        }
        for (Patient patient : patients) {
            if (patient.getPatientName().equals(updateAppointment.getPatientName())) {
                checkPatient = true;
            }
        }

        if (checkDoctor == false && checkPatient == false) {
            log.info("Doctor and patient not exists!");
            log.trace("Doctor with doctor name " + updateAppointment.getDoctorName() + " and patient with name: " + updateAppointment.getPatientName());
            UpdateAppointmentResponse response = new UpdateAppointmentResponse();
            response.setResponseDescription("We can not do the update because the Doctor and the Patient do not exist!");
            return new ResponseEntity<UpdateAppointmentResponse>(response, HttpStatus.NOT_FOUND);
        } else if (checkPatient == false) {
            log.info("Patient not exists!");
            log.trace("Patient with patient name " + updateAppointment.getPatientName());
            UpdateAppointmentResponse response = new UpdateAppointmentResponse();
            response.setResponseDescription("We can not do the update because the Patient does not exist!");
            return new ResponseEntity<UpdateAppointmentResponse>(response, HttpStatus.NOT_FOUND);
        } else if (checkDoctor == false) {
            log.info("Doctor not exists!");
            log.trace("Doctor with doctor name " + updateAppointment.getDoctorName());
            UpdateAppointmentResponse response = new UpdateAppointmentResponse();
            response.setResponseDescription("We can not do the update because the Doctor does not exist!");
            return new ResponseEntity<UpdateAppointmentResponse>(response, HttpStatus.NOT_FOUND);
        }

        //if the appointment is identical
        for (Appointment appointment : appointments) {
            if (updateAppointment.getAppointmentId().equals(appointment.getAppointmentId())) {
                if (checkUpdateAppointment(appointment, updateAppointment)) {
                    log.info("Appointment already updated!");
                    log.trace("Appointment with doctor name " + updateAppointment.getDoctorName() + ", " + "with Patient name: " +
                            updateAppointment.getPatientName() + ", " + "with Date : " + updateAppointment.getAppointmentDate());
                    UpdateAppointmentResponse response = new UpdateAppointmentResponse();
                    response.setResponseDescription("Request identical with entry");
                    return new ResponseEntity<UpdateAppointmentResponse>(response, HttpStatus.CONFLICT);
                }else{
                    //if not, update him
                    log.info("Called /appointment/create");
                    log.trace("Called /appointment/create with doctor name " + updateAppointment.getDoctorName() + ", " + "with Patient name: " +
                            updateAppointment.getPatientName() + ", " + "with Date : " + updateAppointment.getAppointmentDate());
                    log.debug("Called /appointment/create at " + LocalDate.now());
                    appointment.setDoctorName(updateAppointment.getDoctorName());
                    appointment.setPatientName(updateAppointment.getPatientName());
                    appointment.setAppointmentDate(updateAppointment.getAppointmentDate());
                    appointment.setAppointmentTime(updateAppointment.getAppointmentTime());

                    ErrorResponse response = new ErrorResponse();
                    response.setErrorDescription("Successfully update!");
                    log.info("Appointment update successful");
                    log.debug("Appointment found and updated :" + response.toString());
                    return new ResponseEntity<ErrorResponse>(response, HttpStatus.OK);
                }
            }
        }

        //if not, update him to the list
        UpdateAppointmentResponse response = new UpdateAppointmentResponse();
        response.setResponseDescription("Appointment with id " + updateAppointment.getAppointmentId() + " was not found!");
        log.info("No appointment found!");
        log.debug("No appointments found with appointmentDates: " + updateAppointmentRequest.getAppointment());
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);

    }

    @Override
    public ResponseEntity<?> deleteAppointment(Optional<Integer> appointmentId, HttpServletResponse httpServletResponse) {

        for(Appointment appointment: appointments) {
            //check if the id we want to delete exists
            if(appointment.getAppointmentId().equals(appointmentId.get())) {
                appointments.remove(appointment);
                DeleteAppointmentResponse response = new DeleteAppointmentResponse();
                response.setResponseDescription("Appointment with id " + appointmentId.get() + " has been deleted!");
                log.info("Appointment deleted!");
                log.debug("Appointments with appointmentDates: " + appointmentId.get() + "has been deleted!");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        }


        //if not, return response
        DeleteAppointmentResponse response = new DeleteAppointmentResponse();
        response.setResponseDescription("Appointment with id " + appointmentId.get() + " is not in the list!");
        log.info("Appointment not deleted!");
        log.debug("Appointments with appointmentDates: " + appointmentId.get() + "has not been found!");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

    }


    private boolean checkAddAppointment(Appointment appointment, AddAppointment addAppointment){
        if(appointment.getDoctorName().equals(addAppointment.getDoctorName()) &&
                appointment.getPatientName().equals(addAppointment.getPatientName()) &&
                appointment.getAppointmentDate().equals((addAppointment.getAppointmentDate()))){
            return true;
        } else {
            return false;
        }

    }

    //check if there is already an apppointement made for the sepcified doctor, at the specified date and hour
    private boolean checkDateTime(Appointment appointment, AddAppointment addAppointment){
        String doctor = addAppointment.getDoctorName();
        LocalDate date = addAppointment.getAppointmentDate();
        LocalTime hour = addAppointment.getAppointmentTime();
        if (doctor.equals(appointment.getDoctorName()) &&
                date.equals(appointment.getAppointmentDate())&&
                hour.equals(appointment.getAppointmentTime())) {
            return true;
        }else{
            return false;
        }
    }

    private boolean checkUpdateAppointment(Appointment appointment, UpdateAppointment updateAppointment){
        if(appointment.getDoctorName().equals(updateAppointment.getDoctorName()) &&
                appointment.getPatientName().equals(updateAppointment.getPatientName()) &&
                appointment.getAppointmentDate().equals((updateAppointment.getAppointmentDate()))){
            return true;
        } else {
            return false;
        }

    }



    //daca nu mai exista in lista data si ora pentru doctorul respectiv

}
