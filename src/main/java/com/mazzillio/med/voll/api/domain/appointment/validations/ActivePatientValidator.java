package com.mazzillio.med.voll.api.domain.appointment.validations;

import com.mazzillio.med.voll.api.domain.address.PatientRepository;
import com.mazzillio.med.voll.api.domain.appointment.SchedulerAppointmentData;
import jakarta.validation.ValidationException;

public class ActivePatientValidator {
    private PatientRepository patientRepository;
    public void validate(SchedulerAppointmentData data) {
        boolean isActivePatient = patientRepository.findActiveById(data.idPatient());
        if(!isActivePatient) {
            throw new ValidationException("Appointment cant be schedule for exclude patient");
        }
    }
}
