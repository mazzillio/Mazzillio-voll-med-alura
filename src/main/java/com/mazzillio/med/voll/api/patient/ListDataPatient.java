package com.mazzillio.med.voll.api.patient;

public record ListDataPatient(String name, String email, String phone) {
    public ListDataPatient(Patient patient) {
        this(patient.getName(), patient.getEmail(), patient.getPhone());
    }
}
