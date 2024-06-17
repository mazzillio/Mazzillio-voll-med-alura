package com.mazzillio.med.voll.api.domain.appointment.validations;

import com.mazzillio.med.voll.api.domain.appointment.SchedulerAppointmentData;
import jakarta.validation.ValidationException;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class HourClinicValidator {

    public void validate(SchedulerAppointmentData data) {
        LocalDateTime scheduleData = data.data();
        boolean sunday = scheduleData.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        boolean beforeOpen = scheduleData.getHour() < 7;
        boolean afterClose = scheduleData.getHour() > 18;
        if (sunday || beforeOpen || afterClose) {
            throw new ValidationException("Schedule out of working");
        }
    }
}
