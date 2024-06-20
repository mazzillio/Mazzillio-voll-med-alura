package com.mazzillio.med.voll.api.domain.appointment.validations;

import com.mazzillio.med.voll.api.domain.ServiceExceptionValidation;
import com.mazzillio.med.voll.api.domain.appointment.SchedulerAppointmentData;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

@Component
public class HourClinicValidator implements AppointmentValidator{

    public void validate(SchedulerAppointmentData data) {
        LocalDateTime scheduleData = data.data();
        boolean sunday = scheduleData.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        boolean beforeOpen = scheduleData.getHour() < 7;
        boolean afterClose = scheduleData.getHour() > 18;
        if (sunday || beforeOpen || afterClose) {
            throw new ServiceExceptionValidation("Schedule out of working");
        }
    }
}
