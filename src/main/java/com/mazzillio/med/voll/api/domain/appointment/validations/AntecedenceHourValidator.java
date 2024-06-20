package com.mazzillio.med.voll.api.domain.appointment.validations;

import com.mazzillio.med.voll.api.domain.ServiceExceptionValidation;
import com.mazzillio.med.voll.api.domain.appointment.SchedulerAppointmentData;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class AntecedenceHourValidator implements AppointmentValidator{
    public void validate(SchedulerAppointmentData data) {
        LocalDateTime scheduleData = data.data();
        LocalDateTime now = LocalDateTime.now();
        long minutesDifference = Duration.between(now, scheduleData).toMinutes();
        if (minutesDifference < 30) {
            throw new ServiceExceptionValidation("Schedule must be appointment with 30 minutes antecedence");
        }
    }
}
