package com.mazzillio.med.voll.api.doctor;

import com.mazzillio.med.voll.api.address.AddressData;

public record CreateDoctorData(String name, String email, String crm, Specialty specialty, AddressData address) {
}
