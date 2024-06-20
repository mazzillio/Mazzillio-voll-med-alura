package com.mazzillio.med.voll.api.domain.appointment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    boolean existsByDoctorIdAndData(Long aLong, LocalDateTime data);

    boolean existsByPatientIdAndDataBetween(Long id, LocalDateTime firstTime, LocalDateTime lastTime);
}
