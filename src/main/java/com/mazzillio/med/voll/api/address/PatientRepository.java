package com.mazzillio.med.voll.api.address;

import com.mazzillio.med.voll.api.patient.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
