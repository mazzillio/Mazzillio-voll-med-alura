package com.mazzillio.med.voll.api.domain.appointment.validations;

import com.mazzillio.med.voll.api.domain.ServiceExceptionValidation;
import com.mazzillio.med.voll.api.domain.appointment.SchedulerAppointmentData;
import com.mazzillio.med.voll.api.domain.doctor.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActiveDoctorValidator  implements AppointmentValidator{
    @Autowired
    private DoctorRepository doctorRepository;
    public void validate(SchedulerAppointmentData data) {
        if(data.idDoctor() == null) {
            return;
        }
        boolean doctorIsActive = doctorRepository.findActiveById(data.idDoctor());
        if(!doctorIsActive) {
            throw new ServiceExceptionValidation("Schedule with doctor cant be done");
        }
    }
}
