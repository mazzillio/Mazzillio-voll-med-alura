package com.mazzillio.med.voll.api.doctor;

public record ListDataDoctor(String name, String email, String crm, Specialty specialty) {
    public ListDataDoctor(Doctor doctor) {
        this(doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getSpecialty());
    }
}
