package com.mazzillio.med.voll.api.domain.appointment.validations;

import com.mazzillio.med.voll.api.domain.ServiceExceptionValidation;
import com.mazzillio.med.voll.api.domain.appointment.SchedulerAppointmentData;
import com.mazzillio.med.voll.api.domain.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActivePatientValidator implements AppointmentValidator{
    @Autowired
    private PatientRepository patientRepository;
    public void validate(SchedulerAppointmentData data) {
        boolean isActivePatient = patientRepository.findActiveById(data.idPatient());
        if(!isActivePatient) {
            throw new ServiceExceptionValidation("Appointment cant be schedule for exclude patient");
        }
    }
}
