package com.mazzillio.med.voll.api.domain.appointment.validations;

import com.mazzillio.med.voll.api.domain.appointment.SchedulerAppointmentData;
import jakarta.validation.ValidationException;

import java.time.Duration;
import java.time.LocalDateTime;

public class AntecedenceHourValidator {
    public void validate(SchedulerAppointmentData data) {
        LocalDateTime scheduleData = data.data();
        LocalDateTime now = LocalDateTime.now();
        long minutesDifference = Duration.between(now, scheduleData).toMinutes();
        if (minutesDifference < 30) {
            throw new ValidationException("Schedule must be appointment with 30 minutes antecedence");
        }
    }
}
