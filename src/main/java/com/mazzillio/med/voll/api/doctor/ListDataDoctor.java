package com.mazzillio.med.voll.api.doctor;

public record ListDataDoctor(Long Id, String name, String email, String phone, String crm, Specialty specialty) {
    public ListDataDoctor(Doctor doctor) {
        this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getPhone(), doctor.getCrm(), doctor.getSpecialty());
    }
}
