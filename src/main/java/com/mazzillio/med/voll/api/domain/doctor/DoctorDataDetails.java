package com.mazzillio.med.voll.api.domain.doctor;

import com.mazzillio.med.voll.api.domain.address.Address;

public record DoctorDataDetails(Long id, String name, String phone, String email, String crm, Specialty specialty,
                                Address address) {
    public DoctorDataDetails(Doctor doctorData) {
        this(doctorData.getId(), doctorData.getName(), doctorData.getPhone(), doctorData.getEmail(), doctorData.getCrm(), doctorData.getSpecialty(), doctorData.getAddress());
    }
}
