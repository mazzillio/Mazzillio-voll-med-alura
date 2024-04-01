package com.mazzillio.med.voll.api.domain.appointment;

import jakarta.validation.constraints.NotNull;

public record SchedulerCancelData(
        @NotNull
        Long idAppointment,
        @NotNull
        CancelReason reason
) {
}
