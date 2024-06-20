package com.mazzillio.med.voll.api.domain.appointment.validations;

import com.mazzillio.med.voll.api.domain.ServiceExceptionValidation;
import com.mazzillio.med.voll.api.domain.appointment.Appointment;
import com.mazzillio.med.voll.api.domain.appointment.AppointmentRepository;
import com.mazzillio.med.voll.api.domain.appointment.SchedulerCancelData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidatorTimeAheadOfDay implements ValidatorCancelAppointment{
    @Autowired
    private AppointmentRepository appointmentRepository;

    public void validate(SchedulerCancelData data) {
        Appointment appointment = appointmentRepository.getReferenceById(data.idAppointment());
        LocalDateTime now = LocalDateTime.now();
        long hoursDifference = Duration.between(now, appointment.getData()).toHours();
        if(hoursDifference < 24) {
            throw new ServiceExceptionValidation("Appointment just be cancel with advance 24 hours");
        }
    }
}
