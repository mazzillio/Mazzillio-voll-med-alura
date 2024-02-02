package com.mazzillio.med.voll.api.domain.patient;

public record ListDataPatient(Long id, String name, String email, String phone) {
    public ListDataPatient(Patient patient) {
        this(patient.getId(), patient.getName(), patient.getEmail(), patient.getPhone());
    }
}
