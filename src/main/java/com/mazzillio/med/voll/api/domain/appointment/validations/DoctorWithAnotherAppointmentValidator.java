package com.mazzillio.med.voll.api.domain.appointment.validations;

import com.mazzillio.med.voll.api.domain.appointment.AppointmentRepository;
import com.mazzillio.med.voll.api.domain.appointment.SchedulerAppointmentData;
import jakarta.validation.ValidationException;

public class DoctorWithAnotherAppointmentValidator {
    private AppointmentRepository appointmentRepository;
    public void validate(SchedulerAppointmentData data) {
        boolean doctorHasAnotherAppointmentInTheSameHour =
                appointmentRepository.existsByDoctorIdAndData(data.idDoctor(), data.data());
        if(doctorHasAnotherAppointmentInTheSameHour) {
            throw new ValidationException("Doctor already has appointment in the same hour");
        }
    }
}
