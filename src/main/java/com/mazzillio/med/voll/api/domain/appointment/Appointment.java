package com.mazzillio.med.voll.api.domain.appointment;

import com.mazzillio.med.voll.api.domain.doctor.Doctor;
import com.mazzillio.med.voll.api.domain.patient.Patient;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "appointments")
@Entity(name = "Appointment")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="patient_id")
    private Patient patient;

    private LocalDateTime data;

    @Column(name = "cancel_reason")
    @Enumerated(EnumType.STRING)
    private CancelReason cancelReason;

    public void cancel(CancelReason reason) {
        this.cancelReason = reason;
    }
}
