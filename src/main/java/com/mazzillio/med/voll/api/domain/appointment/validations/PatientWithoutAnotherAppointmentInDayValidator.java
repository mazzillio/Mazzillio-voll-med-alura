package com.mazzillio.med.voll.api.domain.appointment.validations;

import com.mazzillio.med.voll.api.domain.appointment.AppointmentRepository;
import com.mazzillio.med.voll.api.domain.appointment.SchedulerAppointmentData;
import jakarta.validation.ValidationException;

import java.time.LocalDateTime;

public class PatientWithoutAnotherAppointmentInDayValidator {
    private AppointmentRepository appointmentRepository;
   public void validate(SchedulerAppointmentData data) {
       LocalDateTime firstTime = data.data().withHour(7);
       LocalDateTime lastTime = data.data().withHour(18);
       boolean patientHasAnotherAppointmentInDay = appointmentRepository.existsPatientIdAndDataBetween(data.idPatient(),firstTime,lastTime);
       if(patientHasAnotherAppointmentInDay) {
           throw new ValidationException("Patient already has another appointment in the day");
       }
   }
}
