package com.mazzillio.med.voll.api.domain.appointment.validations;

import com.mazzillio.med.voll.api.domain.ServiceExceptionValidation;
import com.mazzillio.med.voll.api.domain.appointment.AppointmentRepository;
import com.mazzillio.med.voll.api.domain.appointment.SchedulerAppointmentData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DoctorWithAnotherAppointmentValidator implements AppointmentValidator{
    @Autowired
    private AppointmentRepository appointmentRepository;
    public void validate(SchedulerAppointmentData data) {
        boolean doctorHasAnotherAppointmentInTheSameHour =
                appointmentRepository.existsByDoctorIdAndData(data.idDoctor(), data.data());
        if(doctorHasAnotherAppointmentInTheSameHour) {
            throw new ServiceExceptionValidation("Doctor already has appointment in the same hour");
        }
    }
}
