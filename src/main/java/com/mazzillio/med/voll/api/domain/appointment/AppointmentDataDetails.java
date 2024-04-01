package com.mazzillio.med.voll.api.domain.appointment;

import java.time.LocalDateTime;

public record AppointmentDataDetails(Long id, Long idDoctor, Long idPatient, LocalDateTime data) {
}
