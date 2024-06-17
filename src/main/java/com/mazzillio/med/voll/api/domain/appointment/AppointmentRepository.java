package com.mazzillio.med.voll.api.domain.appointment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    boolean existsByDoctorIdAndData(Long aLong, LocalDateTime data);

    boolean existsPatientIdAndDataBetween(Long aLong, LocalDateTime firstTime, LocalDateTime lastTime);
}
