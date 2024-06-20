package com.mazzillio.med.voll.api.domain.appointment.validations;

import com.mazzillio.med.voll.api.domain.appointment.SchedulerCancelData;

public interface ValidatorCancelAppointment {
    void validate(SchedulerCancelData data);
}
