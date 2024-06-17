package com.mazzillio.med.voll.api.domain.appointment.validations;

import com.mazzillio.med.voll.api.domain.appointment.SchedulerAppointmentData;
import com.mazzillio.med.voll.api.domain.doctor.DoctorRepository;
import jakarta.validation.ValidationException;

public class ActiveDoctorValidator {
    private DoctorRepository doctorRepository;
    public void validate(SchedulerAppointmentData data) {
        if(data.idDoctor() == null) {
            return;
        }
        boolean doctorIsActive = doctorRepository.findActiveById(data.idDoctor());
        if(!doctorIsActive) {
            throw new ValidationException("Schedule with doctor cant be done");
        }
    }
}
