package com.mazzillio.med.voll.api.domain.appointment.validations;

import com.mazzillio.med.voll.api.domain.ServiceExceptionValidation;
import com.mazzillio.med.voll.api.domain.appointment.AppointmentRepository;
import com.mazzillio.med.voll.api.domain.appointment.SchedulerAppointmentData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PatientWithoutAnotherAppointmentInDayValidator implements AppointmentValidator{
    @Autowired
    private AppointmentRepository appointmentRepository;
   public void validate(SchedulerAppointmentData data) {
       LocalDateTime firstTime = data.data().withHour(7);
       LocalDateTime lastTime = data.data().withHour(18);
       boolean patientHasAnotherAppointmentInDay = appointmentRepository.existsByPatientIdAndDataBetween(data.idPatient(),firstTime,lastTime);
       if(patientHasAnotherAppointmentInDay) {
           throw new ServiceExceptionValidation("Patient already has another appointment in the day");
       }
   }
}
