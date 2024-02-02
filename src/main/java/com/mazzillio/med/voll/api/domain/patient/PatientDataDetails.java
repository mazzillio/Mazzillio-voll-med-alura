package com.mazzillio.med.voll.api.domain.patient;

import com.mazzillio.med.voll.api.domain.address.Address;

public record PatientDataDetails(Long id, String name, String phone, String email, Address address) {
    public PatientDataDetails(Patient patient) {
        this(patient.getId(), patient.getName(), patient.getPhone(), patient.getEmail(), patient.getAddress());
    }
}
