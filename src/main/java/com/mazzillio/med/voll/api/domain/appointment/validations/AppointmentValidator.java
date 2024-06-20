package com.mazzillio.med.voll.api.domain.appointment.validations;

import com.mazzillio.med.voll.api.domain.appointment.SchedulerAppointmentData;

public interface AppointmentValidator {
    void validate(SchedulerAppointmentData data);
}
