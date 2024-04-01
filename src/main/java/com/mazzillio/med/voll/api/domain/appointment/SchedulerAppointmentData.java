package com.mazzillio.med.voll.api.domain.appointment;

import com.mazzillio.med.voll.api.domain.doctor.Specialty;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record SchedulerAppointmentData(
        Long idDoctor,
        @NotNull
        Long idPatient,
        @NotNull
        @Future
        LocalDateTime data,

        Specialty specialty
) {
}
